package net.media.dbtest.Models;

import com.google.gson.JsonObject;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Config {

    private String adId;
    private JsonObject data;

    public Config(String adId, JsonObject data) {
        this.adId = adId;
        this.data = data;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Config{" +
                "adId=" + adId +
                ", data=" + data +
                '}';
    }
}
