package verticle;

import annotation.ServiceHandler;
import com.google.inject.name.Named;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import javax.inject.Inject;
import java.util.List;

public class RestVerticle extends AbstractVerticle{

    @Inject
    List<ServiceHandler> handlers;

    @Inject
    @Named("port")
    int port;

    @Inject
    HttpServer httpServer;

    @Inject
    Router router;

    @Override
    public void init(Vertx vertx, Context context) {
        super.init(vertx, context);
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        super.start();
        System.out.println("Initializing the verticle");
        // To allow to read body POST data..
        router.route().handler(BodyHandler.create());
        handlers
                .forEach(serviceHandler -> serviceHandler.init(router));
        //initialize service router class
        httpServer
                .requestHandler(router::accept)
                .listen(port);
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
httpServer.close();
    }
}
