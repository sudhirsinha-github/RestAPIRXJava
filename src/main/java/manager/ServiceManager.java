package manager;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rx.java.RxHelper;
import rx.Observable;

public class ServiceManager {
    @Inject
    @Named("httpsClient")
    protected HttpClient httpsClient;

    private Logger logger = LoggerFactory.getLogger(ServiceManager.class);
/*
    public ServiceManager(HttpClient httpsClient) {
    }*/

    Observable<HttpClientResponse> callHttps(String url, MultiMap headers, HttpMethod httpMethod) {
        return Observable.create(subscriber -> {
            HttpClientRequest httpClientRequest = null;
            httpClientRequest = httpsClient.requestAbs(httpMethod, url);
            httpClientRequest.headers().addAll(headers);
            httpClientRequest.exceptionHandler(Throwable::printStackTrace);
            RxHelper
                    .toObservable(httpClientRequest)
                    .subscribe(subscriber);
            httpClientRequest.end();
        });
    }
}