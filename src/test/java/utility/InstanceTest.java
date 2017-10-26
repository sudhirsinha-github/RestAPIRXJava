package utility;

import io.vertx.rxjava.core.Vertx;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sudhirkumar on 10/26/17.
 */
public class InstanceTest {
    @Test
    public void getInstance() throws Exception {
        Vertx vertx = Instance.INSTANCE.getInstance();
        assert vertx != null;
    }

}