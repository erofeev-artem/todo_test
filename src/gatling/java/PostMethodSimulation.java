import gatling.Scenario;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class PostMethodSimulation extends Simulation {
//    TodoProperties properties = new PropertiesConverter().ymlToObject(TodoProperties.class, "/todo_properties.yaml");
    HttpProtocolBuilder httpProtocolBuilder = HttpDsl.http.baseUrl("http://127.0.0.1:8080/todos");

    public PostMethodSimulation() {
        this.setUp(Scenario.post.injectOpen(CoreDsl.constantUsersPerSec(1).during(1)))
                .protocols(httpProtocolBuilder);
    }
}
