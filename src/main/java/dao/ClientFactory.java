package dao;

import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.ext.mongo.MongoClient;
import utility.Instance;

public class ClientFactory {
    public MongoClient getMongoInstance(JsonObject mongoconfig)
    {
        return MongoClient
                .createShared(Instance.INSTANCE.getInstance(), mongoconfig, "TTye");
    }
}
