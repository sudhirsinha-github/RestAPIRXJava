import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.Configuration;
import io.vertx.core.DeploymentOptions;
import utility.Instance;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by sudhirkumar on 9/19/17.
 */
public class ServiceDeployer {

    public static void main(String[] args) {
        String configPath = args[0];
        ObjectMapper mapper = new ObjectMapper();

        Configuration configuration = null;
        try {
            configuration = mapper.readValue(new String(Files
                    .readAllBytes(Paths.get(configPath))), Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DeploymentOptions deploymentOptions = new DeploymentOptions()
                .setInstances(configuration.getInstance())
                .setConfig(configuration.getSetting());

        Instance.INSTANCE.getInstance().deployVerticle(configuration.getVerticle(), deploymentOptions,
                event -> {
                    System.out.println(event.result());
                    System.out.println(event.cause());

                });
    }

    public static String getJsonSampleData(String fileName){
        try {
            return new String(Files
                    .readAllBytes(Paths.get(fileName))
            );
        }
        catch (IOException ex)
        {
            System.out.println("Error while reading json file -- " + fileName);
            return "";
        }
    }
}
