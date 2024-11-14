import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.todo.client.TodoClient;
import org.todo.dto.TodoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;

public class TodoPostMethodSimulation extends BaseTest {

    private TodoClient client;

    @BeforeEach
    void before() {
        client = new TodoClient(false);
    }

    static Stream<Object[]> testDataPostMethod() {
        List<Object[]> cases = new ArrayList<>();
        cases.add(new Object[]{11, "to write a test", false,
                new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_CREATED)
                        .expectBody(is(emptyString())).build()});
        cases.add(new Object[]{0, "to write a test", true,
                new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_CREATED)
                        .expectBody(is(emptyString())).build()});
        cases.add(new Object[]{-1, "to write a test", false,
                new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                        .expectBody(containsString("Request body deserialize error")).build()});
        cases.add(new Object[]{9223372036854775807L, "to write a test", true,
                new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_CREATED)
                        .expectBody(is(emptyString())).build()});
        cases.add(new Object[]{1, "", true,
                new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_CREATED)
                        .expectBody(is(emptyString())).build()});
        cases.add(new Object[]{1001, null, false,
                new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                        .expectBody(containsString("Request body deserialize error")).build()});
        return cases.stream();
    }

    @ParameterizedTest
    @MethodSource("testDataPostMethod")
    void createTodoTest(long id, String text, boolean completed, ResponseSpecification respSpec) {
        TodoDto dto = new TodoDto(id, text, completed);
        client.post(dto).then().spec(respSpec);
    }
}