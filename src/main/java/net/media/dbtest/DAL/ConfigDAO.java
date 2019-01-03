package net.media.dbtest.DAL;
import net.media.dbtest.Models.Config;
import org.bson.Document;

import java.util.List;

public interface ConfigDAO {

    void save(Config config);
    void save(List<Config> configs);
    List<Document> getAllConfig();
    List<Document> getByAdId(String id);
    Document getbyId(String id);
    List<Document> filterByDimensionConfig(String ... args);
    List<Document> getHighestPriority(String... args);

}
