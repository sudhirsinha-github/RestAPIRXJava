package manager;

import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.rx.java.RxHelper;
import rx.Observable;

/**
 * Created by sudhirkumar on 9/19/17.
 */
public class ServiceProxy extends ServiceManager {

    Observable<JsonObject> getData(String url, HttpMethod httpMethod) {
        return callHttps(url, getHeaders(), httpMethod)
                .doOnNext(httpClientResponse -> System.out.println(httpClientResponse))
                .concatMap(RxHelper::toObservable)
                .reduce(Buffer.buffer(),Buffer::appendBuffer)
                .map(Buffer::toJsonObject)
                ;
    }

    private MultiMap getHeaders()
    {
        MultiMap header = MultiMap.caseInsensitiveMultiMap();
        header.add("Content-Type","application/json");
        return header;
    }
}
