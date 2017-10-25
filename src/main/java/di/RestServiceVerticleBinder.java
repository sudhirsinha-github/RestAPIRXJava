package di;

import annotation.ServiceHandler;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import handler.GetList;
import handler.UpdateList;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sudhirkumar on 9/19/17.
 */
public class RestServiceVerticleBinder extends AbstractModule {
    @Override
    protected void configure()
    {
        bind(GetList.class);
        bind(UpdateList.class);
    }


    @Provides
    public List<ServiceHandler> getAPIHandlers(GetList list, UpdateList updateList) {
        return Arrays.asList(list, updateList);
    }

    @Provides
    public Router getRouter(Vertx vertx) {
        return Router.router(vertx);
    }

    @Provides
    public HttpServer getHttpServer(Vertx vertx) {
        return vertx.createHttpServer();
    }

    @Provides
    @Singleton
    @Named("port")
    public int getPort(Vertx vertx) {
        return vertx.getOrCreateContext().config().getInteger("service_port");
    }

    @Provides
    @Named("httpsClient")
    public HttpClient getHttpsClient(Vertx vertx) {

        HttpClientOptions httpClientOptions = new HttpClientOptions()
//				.setConnectTimeout(configuration.getConnectionTimeOut())
//				.setIdleTimeout(configuration.getIdleTimeOut())
//				.setKeepAlive(configuration.isAlive())
//				.setTryUseCompression(configuration.isCompressed())
                .setSsl(true)
                .setTrustAll(true)
                .setVerifyHost(false);

        return vertx.createHttpClient(httpClientOptions);
    }

}
