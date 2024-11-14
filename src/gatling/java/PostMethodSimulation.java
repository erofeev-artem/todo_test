import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.http.HttpDsl.http;

public class PostMethodSimulation extends Simulation {
    HttpProtocolBuilder httpProtocolBuilder = http.baseUrl("http://127.0.0.1:8080");
    ScenarioBuilder myScenario = Scenario.post;

    {
        setUp(
                myScenario.injectOpen(constantUsersPerSec(30).during(60))
        ).protocols(httpProtocolBuilder);
    }
}

