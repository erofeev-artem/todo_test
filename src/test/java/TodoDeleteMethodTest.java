import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.todo.client.TodoClient;
import org.todo.dto.TodoDto;

import static org.hamcrest.Matchers.is;

public class TodoDeleteMethodTest extends BaseTest {
    private TodoClient client;
    private TodoClient authorizedClient;

    @BeforeEach
    void before() {
        client = new TodoClient(false);
        authorizedClient = new TodoClient(true);
        for (int i = 0; i < 3; i++) {
            TodoDto dto = new TodoDto(i, "make test " + i, i % 2 == 0);
            client.post(dto);
        }
    }

    @Test
    void deleteTodoTest() {
        client.get().then().body("size()", is(3));
        authorizedClient.delete(1).then().statusCode(HttpStatus.SC_NO_CONTENT);
        client.get().then().body("size()", is(2));
    }

    @Test
    void deleteTodoNotFoundTest() {
        authorizedClient.delete(10).then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void deleteTodoWithoutAuthenticationTest() {
        client.delete(2).then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    void deleteTodoTwiceTest() {
        authorizedClient.delete(0).then().statusCode(HttpStatus.SC_NO_CONTENT);
        authorizedClient.delete(0).then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void deleteTodoAndAddTest() {
        authorizedClient.delete(0).then().statusCode(HttpStatus.SC_NO_CONTENT);
        client.post(new TodoDto(0, "make test 0", true));
        ResponseSpecification respSpec = new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_OK)
                .expectBody("size()", is(3))
                .expectBody("id[2]", is(0))
                .expectBody("text[2]", is("make test 0"))
                .expectBody("completed[2]", is(true))
                .build();
        client.get().then().spec(respSpec);
    }

}
