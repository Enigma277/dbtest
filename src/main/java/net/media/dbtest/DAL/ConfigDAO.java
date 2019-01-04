package net.media.dbtest.DAL;
import com.mongodb.client.ClientSession;
import net.media.dbtest.Models.Config;
import org.bson.Document;

import java.util.List;

public interface ConfigDAO {

    void save(ClientSession clientSession , String database , String collection, Config config);
    void save(ClientSession clientSession , String database , String collection, List<Config> configs);
    void updatePriority(ClientSession clientSession ,String database , String collection, String adId, int priority);
    List<Document> getAllConfig(String database , String collection);
    List<Document> getByAdId(String database,String collection,  String id);
    Document getbyId(String database , String collection, String id);
    List<Document> filterByDimensionConfig(String ... args);
    List<Document> getHighestPriority(String... args);
}
