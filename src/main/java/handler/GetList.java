package handler;

import annotation.Service;
import annotation.ServiceHandler;
import dao.Mongo;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import rx.Observable;

import javax.inject.Inject;

@Service(Url = "/list", version = "v2", method = HttpMethod.GET)
public class GetList extends ServiceHandler {
    @Inject
    Mongo mongo ;

    @Override
    public void handle(RoutingContext context) {

        JsonObject mongoconfig = new JsonObject()
                .put("connection_string", "mongodb://localhost:27017")
                .put("db_name", "nextgencec");
        Mongo.mongoconfig= (mongoconfig);

        mongo.findData(new JsonObject())
        .subscribe(
                        data ->
                this.responseProcessor(context, 200, data.toString()),
                throwable -> System.out.println(throwable.getMessage())
        );
    }
}
