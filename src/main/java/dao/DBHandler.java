package dao;


import io.vertx.core.json.JsonObject;
import rx.Observable;

import java.util.List;

public interface DBHandler {
    Observable<String> insertData(JsonObject jsonObject);
    Observable<Boolean> update(JsonObject jsonQuery, JsonObject update);
}
