import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;

public class Steps extends Simulation {
    private static final String path = "/todos";

    public static ChainBuilder post = CoreDsl.exec(
            HttpDsl.http("post")
                    .post(path)
                    .body(CoreDsl.StringBody("{\"id\": 74523 , \"text\": \"leader\" , \"completed\": false}"))
                    .check(HttpDsl.status().is(201)));
}
