package dao;

import com.google.inject.Inject;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.rxjava.ext.mongo.MongoClient;
import rx.Observable;

import java.util.List;

public class Mongo implements DBHandler{
    MongoClient mongoClient ;
    public static JsonObject mongoconfig;

    @Inject
    ClientFactory factory;

    public MongoClient getMongoClient(JsonObject mongoconfig){
        this.mongoClient = factory.getMongoInstance(mongoconfig);
        return this.mongoClient;
    }

    /**
     * authorizeUser :: Find the user in DB
     * @param jsonQuery
     * @return
     */
    public Observable<List<JsonObject>> authorizeUser(JsonObject jsonQuery) {
        this.mongoClient =  getMongoClient(mongoconfig);
        FindOptions findOptions = new FindOptions()
                //.setLimit(4)
                //.setSkip(1)
                .setFields(new JsonObject()./*put("name", 1).*/put("_id", 0))
               // .setSort(new JsonObject().put("name", -1))
        ;

        return findData(jsonQuery)
                //.delay(10, TimeUnit.SECONDS)
                .doOnNext(t -> isAuthorizedUser(t))
                .doOnNext(t -> System.out.println(t));
    }

    public Observable<List<JsonObject>> findData(JsonObject jsonQuery) {
        this.mongoClient =  getMongoClient(mongoconfig);

        FindOptions findOptions = new FindOptions()
                //.setLimit(4)
                //.setSkip(1)
                .setFields(new JsonObject()./*put("name", 1).*/put("_id", 0));


        return mongoClient
                .findWithOptionsObservable("TestCustomer", jsonQuery, findOptions);
    }

    private void isAuthorizedUser(List<JsonObject> t) {
        if(t.size() < 1)
        {
         throw new RuntimeException("User is not authorized!!");
        }
    }

    public Observable<Boolean> update(JsonObject jsonQuery, JsonObject update) {
        this.mongoClient =  getMongoClient(mongoconfig);

        return mongoClient.updateCollectionObservable("TestCustomer", jsonQuery, update)
                .doOnNext(System.out::println)
                .map(res -> res.getDocModified() > 0 || res.getDocMatched() > 0);
    }

    public Observable<String> insertData(JsonObject jsonObject) {
        return mongoClient.insertObservable("TestCustomer", jsonObject);
    }

   /* public static void main(String[] args) {

        JsonObject mongoconfig = new JsonObject()
                .put("connection_string", "mongodb://localhost:27017")
                .put("db_name", "nextgencec");


        Mongo mongo = new Mongo(mongoconfig);
        JsonObject query = new JsonObject();
        query.put("name","Suuuull").put("company","Zen");

        //>db.mycol.update({'title':'MongoDB Overview'},
        //                           {$set:{'title':'New MongoDB Tutorial'}})

        mongo.update(new JsonObject().put("name","Sachin10"),
                new JsonObject().put("$set", query))
                .doOnNext(i -> System.out.println("#####" + i))
                .subscribe(
                        res -> System.out.println(res)
                );
    }*/
}
