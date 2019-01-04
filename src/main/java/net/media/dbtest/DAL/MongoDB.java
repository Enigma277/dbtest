package net.media.dbtest.DAL;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import net.media.dbtest.DAL.ConfigDAO;
import net.media.dbtest.Models.Config;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MongoDB implements ConfigDAO {


    private MongoClient mongoClient ;


    private MongoDatabase database ;
    private MongoCollection<Document> collection;

    public MongoDB(){
        this.mongoClient = new MongoClient("localhost" , 28017);
        this.database = mongoClient.getDatabase("Dbtest");
        this.collection = database.getCollection("test");

    }

    public void close(){
        this.mongoClient.close();
    }

    @Override
    public List<Document> getAllConfig(String database, String collection) {
        List<Document> configs = new ArrayList<>();
        MongoCursor<Document> cur = this.mongoClient.getDatabase(database).getCollection(collection).find().iterator();
        while(cur.hasNext()){
            configs.add(cur.next());
        }
        return configs;
    }

    @Override
    public Document getbyId(String database, String collection,String id) {
        return this.mongoClient.getDatabase(database).getCollection(collection).find( BasicDBObject.parse("{\"$_id\" :"+id+" }")).first();
    }

    @Override
    public void updatePriority(ClientSession clientSession , String database, String collection,String adId, int priority) {
        BasicDBObject newDoc = new BasicDBObject();
        newDoc.append("$set",new BasicDBObject().append("data.priority",priority));
        BasicDBObject query = new BasicDBObject().append("adId",adId);
        this.mongoClient.getDatabase(database).getCollection(collection).updateOne(clientSession, query , newDoc);
        System.out.println("Successfully Updated " + query);
    }

    // Getters and Setters
    public MongoClient getMongoClient() {
        return mongoClient;
    }
    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }
    public MongoDatabase getDatabase() {
        return database;
    }
    public void setDatabase(MongoDatabase database) {
        this.database = database;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public void setCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public void save(ClientSession clientSession , String database, String collection , Config config) {
        Gson gson = new Gson();
        Document doc = Document.parse(gson.toJson(config));
        this.mongoClient.getDatabase(database).getCollection(collection).insertOne(clientSession, doc);
        System.out.println("Saved SuccessFully");
    }

    @Override
    public void save(ClientSession clientSession,String database , String collection, List<Config> configs) {
       for(Config config:configs){
          save(clientSession , database, collection, config);
       }
    }

    @Override
    public List<Document> getByAdId(String database, String collection, String adId) {
        List<Document> configs = new ArrayList<>();
        MongoCursor<Document> cur = this.mongoClient.getDatabase(database).getCollection(collection).find( BasicDBObject.parse("{\"$adId\" :"+adId+" }")).iterator();
        while(cur.hasNext()){
            configs.add(cur.next());
        }
        return configs;
    }

    public BasicDBObject getFilterQuery(String ... arguments){
        String queryString ="";
        for(int i=0;i<arguments.length;i+=2){
            queryString+="{\"$or\":[{\"data.where.is." +arguments[i] +"\":{\"$in\":["+"\""+arguments[i+1]+"\"";
            queryString+= "]}},{\"data.where.isNot."+arguments[i]+"\":{\"$nin\":["+ "\""+arguments[i+1]+"\"";
            queryString+="]}}]}";
            if(i+2!=arguments.length){
                queryString+=", ";
            }
        }
        queryString = "{\"$and\":[" + queryString + "]}";
        System.out.println(queryString);
        return BasicDBObject.parse(queryString);
    }

    @Override
    public List<Document> filterByDimensionConfig(String... arguments) {

        BasicDBObject query = getFilterQuery(arguments);
        List<Document> configs = new ArrayList<>();
        MongoCursor<Document> cur = this.collection.find(query).iterator();
        while (cur.hasNext()){
            configs.add(cur.next());
        }
        return configs;
    }

    @Override
    public List<Document> getHighestPriority(String... arguments) {
        BasicDBObject query = getFilterQuery(arguments);
        List<Document> configs = new ArrayList<>();
        MongoCursor<Document> cur = this.collection.aggregate(
                Arrays.asList(
                        Aggregates.match(query),
                        new Document ("$sort",new Document("data.priority",-1)),
                        Aggregates.group("$adId", Accumulators.first("priority","$data.priority"),
                                Accumulators.first("Id" , "$_id")
                        )
                )
        ).iterator();
        while(cur.hasNext()){
            configs.add(cur.next());
        }
        return configs;
    }

    @Override
    public String toString() {
        return "MongoDB{" +
                "mongoClient=" + mongoClient +
                ", database=" + database +
                ", collection=" + collection +
                '}';
    }
}
