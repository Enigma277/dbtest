package net.media.dbtest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.ClientSession;
import net.media.dbtest.DAL.MongoDB;
import net.media.dbtest.Models.Config;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbtestApplication {


    public static void main(String[] args) {
        String  str =
                "\t{ \n" +
                        "\t\t\n" +
                        "\t\t\"where\":\n" +
                        "\t\t\t{\n" +
                        "\t\t\t\t\"is\":\n" +
                        "\t\t\t\t\t{\n" +
                        "\t\t\t\t\t\t\"cc\":[\"US\"],\n" +
                        "\t\t\t\t\t    \"crid\":[\"635031203\"]\n" +
                        "\t\t\t\t\t },\n" +
                        "\t\t\t\t\"isNot\":{\n" +
                        "\t\t\t\t\t        \"device\":[\"desktop\",\"mobile\"],\n" +
                        "\t\t\t\t\t     \t\"domain\":[\"www.jjug.uh\",\"www.ghdgethe.fdt\"]\n" +
                        "\t\t\t\t\t    }\n" +
                        "\t\t\t},\n" +
                        "\t\t\"config\":{\n" +
                        "\t\t\t\t\"56\":{\n" +
                        "\t\t\t\t   \"prvid\":\"56\",\n" +
                        "\t\t\t\t   \"prf\":null,\n" +
                        "\t\t\t\t   \"hb\":null,\n" +
                        "\t\t\t\t   \"br\":null,\n" +
                        "\t\t\t\t   \"fp\":7,\n" +
                        "\t\t\t\t   \"dpsh\":null,\n" +
                        "\t\t\t\t   \"ext_pub_code\":null,\n" +
                        "\t\t\t\t   \"sizes\":null\n" +
                        "\t\t\t    },\n" +
                        "\t\t\t\t\"80\":{\n" +
                        "\t\t\t\t   \"prvid\":\"80\",\n" +
                        "\t\t\t       \"prf\":null,\n" +
                        "\t\t\t       \"hb\":null,\n" +
                        "\t\t\t       \"br\":null,\n" +
                        "\t\t\t       \"fp\":null,\n" +
                        "\t\t\t       \"dpsh\":null,\n" +
                        "\t\t\t       \"ext_pub_code\":null,\n" +
                        "\t\t\t       \"sizes\":null\n" +
                        "\t\t       },\n" +
                        "\t\t       \"82\":{\n" +
                        "\t\t            \"prvid\":\"82\",\n" +
                        "\t\t            \"prf\":null,\n" +
                        "\t\t        \t\"hb\":null,\n" +
                        "\t\t        \t\"br\":null,\n" +
                        "\t\t        \t\"fp\":null,\n" +
                        "\t\t        \t\"dpsh\":null,\n" +
                        "\t\t        \t\"ext_pub_code\":null,\n" +
                        "\t\t        \t\"sizes\":null\n" +
                        "\t\t        },\n" +
                        "\t\t        \"99\":{\n" +
                        "\t\t        \t\"prvid\":\"99\",\n" +
                        "\t\t        \t\"prf\":null,\n" +
                        "\t\t        \t\"hb\":null,\n" +
                        "\t\t        \t\"br\":null,\n" +
                        "\t\t        \t\"fp\":null,\n" +
                        "\t\t        \t\"dpsh\":null,\n" +
                        "\t\t        \t\"ext_pub_code\":null,\n" +
                        "\t\t        \t\"sizes\":null\n" +
                        "\t\t        },\n" +
                        "\t\t        \"10000\":{\n" +
                        "\t\t        \t\"prvid\":\"10000\",\n" +
                        "\t\t        \t\"prf\":null,\n" +
                        "\t\t        \t\"hb\":null,\n" +
                        "\t\t        \t\"br\":null,\n" +
                        "\t\t        \t\"fp\":null,\n" +
                        "\t\t        \t\"dpsh\":null,\n" +
                        "\t\t        \t\"ext_pub_code\":null,\n" +
                        "\t\t        \t\"auto\":null,\"sizes\":null\n" +
                        "\t\t        }\n" +
                        "\t\t    },\n" +
                        "\t\t\"id\":\"31905cf2-72b9-498b-8c49-e8b450359a4f:HP_PROVIDER\",\n" +
                        "\t\t\"priority\":1\n" +
                        "\t}\n";
        System.out.println("DbtestApplication Running");
        MongoDB mongo = new MongoDB();
        System.out.println(mongo);
        JsonParser jsonParser = new JsonParser();
        JsonObject json = (JsonObject) jsonParser.parse(str);
        Config dummyConfig = new Config("dummy2",json);
        System.out.println("transaction ke pehle");
        System.out.println("dbtest " +  mongo.getMongoClient().getDatabase("Dbtest").getCollection("test11").countDocuments());
        System.out.println("dbtest2 " + mongo.getMongoClient().getDatabase("Dbtest2").getCollection("test21").countDocuments());

        try  {
            ClientSession clientSession = mongo.getMongoClient().startSession();
            clientSession.startTransaction();
             mongo.updatePriority(clientSession, "Dbtest","test11","dummy2", 123546);

            System.out.println("Transaction ke beech");
       //    System.out.println(mongo.getByAdId("Dbtest","test11","dummy2"));
            System.out.println("dbtest " + mongo.getMongoClient().getDatabase("Dbtest").getCollection("test11").countDocuments());
            System.out.println("dbtest2 " + mongo.getMongoClient().getDatabase("Dbtest2").getCollection("test21").countDocuments());
           // clientSession.abortTransaction();
            clientSession.commitTransaction();
            clientSession.close();

        }catch(Exception e){System.out.println(e);}
        System.out.println("Transactions ke baad");
       // System.out.println(mongo.getByAdId("Dbtest","test11","dummy2"));
        System.out.println("dbtest " + mongo.getMongoClient().getDatabase("Dbtest").getCollection("test11").countDocuments());
        System.out.println("dbtest2 " +mongo.getMongoClient().getDatabase("Dbtest2").getCollection("test21").countDocuments());


    }

}

