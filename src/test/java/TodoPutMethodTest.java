import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.todo.client.TodoClient;
import org.todo.dto.TodoDto;

import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;

public class TodoPutMethodTest extends BaseTest {

    private TodoClient client;

    @BeforeEach
    void before() {
        client = new TodoClient(false);
        for (int i = 0; i < 2; i++) {
            TodoDto dto = new TodoDto(i, "make test " + i, i % 2 == 0);
            client.post(dto);
        }
    }

    @Test
    void updateWithNullTodoTest() {
        TodoDto dto = new TodoDto(0, null, false);
        client.put(dto, dto.id()).then().statusCode(HttpStatus.SC_UNAUTHORIZED).body(is(emptyString()));
    }

    //    strange behavior: a record with a duplicate id is created
    @Test
    void updateTextOnlyTodoTest() {
        TodoDto dto = new TodoDto("make test 0");
        client.put(dto, 1).then().statusCode(HttpStatus.SC_OK).body(is(emptyString()));
        ResponseSpecification respSpec = new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_OK)
                .expectBody("size()", is(2))
                .expectBody("id[0]", is(0))
                .expectBody("text[0]", is("make test 0"))
                .expectBody("completed[0]", is(true))
                .expectBody("id[1]", is(0))
                .expectBody("text[1]", is("make test 0"))
                .expectBody("completed[1]", is(false))
                .build();
        client.get()
                .then().spec(respSpec);
        new TodoClient(true).delete(0);
    }

    @Test
    void updateTodoTest() {
        ResponseSpecification respSpec = new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_OK)
                .expectBody("size()", is(2))
                .expectBody("id[0]", is(999))
                .expectBody("text[0]", is("test updated"))
                .expectBody("completed[0]", is(true))
                .build();
        TodoDto dto = new TodoDto(999, "test updated", true);
        client.put(dto, 0).then().statusCode(HttpStatus.SC_OK).body(is(emptyString()));
        client.get()
                .then().spec(respSpec);
    }

    @Test
    void updateItselfTodoTest() {
        TodoDto dto = new TodoDto(0, "make test 0", true);
        client.put(dto, dto.id()).then().statusCode(HttpStatus.SC_OK).body(is(emptyString()));
        ResponseSpecification respSpec = new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_OK)
                .expectBody("size()", is(2))
                .expectBody("id[0]", is(0))
                .expectBody("text[0]", is("make test 0"))
                .expectBody("completed[0]", is(true))
                .build();
        client.get()
                .then().spec(respSpec);
    }

    @Test
    void updateWrongIdTodoTest() {
        TodoDto dto = new TodoDto(1, "update test 1", true);
        client.put(dto, 8542545).then().statusCode(HttpStatus.SC_NOT_FOUND).body(is(emptyString()));
    }
}
