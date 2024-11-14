import gatling.Steps;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;

public class Scenario {
    public static ScenarioBuilder post = CoreDsl.scenario("post method scenario")
            .exec(Steps.post);
}
