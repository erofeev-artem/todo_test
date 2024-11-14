import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

public class Scenario extends Simulation {
    public static ScenarioBuilder post = CoreDsl.scenario("post method")
            .exec(Steps.post);
}
