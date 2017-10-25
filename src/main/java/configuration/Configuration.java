package configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;

/**
 * Created by sudhirkumar on 9/19/17.
 */

public class Configuration {
@JsonProperty
    private Setting setting;
@JsonProperty
    private String verticle;
@JsonProperty
    private int instance;

    public JsonObject getSetting () {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return new JsonObject(mapper.writeValueAsString(setting));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String getVerticle ()
    {
        return verticle;
    }

    public void setVerticle (String verticle)
    {
        this.verticle = verticle;
    }

    public int getInstance ()
    {
        return instance;
    }

    public void setInstance (int instance)
    {
        this.instance = instance;
    }
}


class Setting {
    @JsonProperty
    private String guice_binder;

    @JsonProperty
    private int service_port;
}