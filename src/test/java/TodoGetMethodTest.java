import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.todo.client.TodoClient;
import org.todo.dto.TodoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class TodoGetMethodTest extends BaseTest {

    private TodoClient client;

    @BeforeEach
    void before() {
        client = new TodoClient(false);
        for (int i = 0; i < 10; i++) {
            TodoDto dto = new TodoDto(i, "make test " + i, i % 2 == 0);
            client.post(dto);
        }
    }

    static Stream<Object[]> testDataWithOffsetAndLimit() {
        List<Object[]> cases = new ArrayList<>();
        cases.add(new Object[]{0, 0,
                new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_OK)
                        .expectBody("size()", is(0)).build()});
        cases.add(new Object[]{2, 0,
                new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_OK)
                        .expectBody("size()", is(0)).build()});
        cases.add(new Object[]{2, 4,
                new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_OK)
                        .expectBody("size()", is(4)).build()});
        cases.add(new Object[]{0, 2,
                new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_OK)
                        .expectBody("size()", is(2)).build()});
        return cases.stream();
    }

    @ParameterizedTest
    @MethodSource("testDataWithOffsetAndLimit")
    void getWithOffsetAndLimitTest(long offset, long limit, ResponseSpecification respSpec) {
        client.getWithOffsetAndLimit(offset, limit)
                .then().spec(respSpec);
    }

    @Test
    void getWithOffsetTest() {
        ResponseSpecification respSpec = new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_OK)
                .expectBody("size()", is(1))
                .expectBody("id[0]", is(9))
                .expectBody("text[0]", is("make test 9"))
                .expectBody("completed[0]", is(false))
                .build();
        client.getWithOffset(9)
                .then().spec(respSpec);
    }

    @Test
    void getWithLimitTest() {
        ResponseSpecification respSpec = new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_OK)
                .expectBody("size()", is(2))
                .expectBody("id", hasItems(0, 1))
                .expectBody("text", hasItems("make test 0", "make test 1"))
                .expectBody("completed", hasItems(false, true))
                .build();
        client.getWithLimit(2)
                .then().spec(respSpec);
    }

    @Test
    void getTest() {
//        TODO
    }
}
