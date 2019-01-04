package net.media.dbtest.Repositories;

import net.media.dbtest.Models.Config;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ConfigRepository extends MongoRepository<Config,String > {
    Config findBy_id(ObjectId _id);
    List<Config> getAllConfogs();
}
