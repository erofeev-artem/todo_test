import com.google.gson.Gson;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.http.HttpDsl;
import org.apache.http.HttpStatus;
import org.todo.dto.TodoDto;

public class Steps {
    private static final String path = "/todos";

    public static ChainBuilder post = CoreDsl.exec(
            HttpDsl.http("post")
                    .post(path)
                    .body(CoreDsl.StringBody((new Gson().toJson(new TodoDto(11, "load test", false)))))
                    .check(HttpDsl.status().is(HttpStatus.SC_CREATED)));
}
