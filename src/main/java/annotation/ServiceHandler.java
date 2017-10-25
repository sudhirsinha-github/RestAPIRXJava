package annotation;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by sudhirkumar on 9/19/17.
 */
public abstract class ServiceHandler {
    public abstract void handle(RoutingContext context);

    public void init(Router router){
        Service service = this
                .getClass()
                .getAnnotation(Service.class);

        if(service != null)
        {
            router
                    .route(service.method(),"/" +service.version() + service.Url())
                    .handler(this::handle);
        }
    }

    public void responseProcessor(RoutingContext context,int code, String message)
    {
        context.response().setStatusCode(code).end(message);
    }
}
