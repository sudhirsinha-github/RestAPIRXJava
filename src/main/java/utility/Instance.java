package utility;

import io.vertx.rxjava.core.Vertx;

/**
 * Created by sudhirkumar on 9/19/17.
 */
public enum Instance {
    INSTANCE;
    private Vertx vertx = Vertx.vertx();

    public Vertx getInstance()
    {
        return vertx;
    }
}
