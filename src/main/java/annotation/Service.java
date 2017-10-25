package annotation;

import io.vertx.core.http.HttpMethod;

import java.lang.annotation.*;

/**
 * Created by sudhirkumar on 9/19/17.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Service {

    String Url() ;
    String version() default "v1";
    HttpMethod method();
}
