import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.todo.client.TodoClient;
import org.todo.dto.TodoDto;

import java.util.List;

public class BaseTest {

    @BeforeEach
    void purge() {
        TodoClient client = new TodoClient(false);
        TodoClient authorizedClient = new TodoClient(true);

        List<TodoDto> list = client.get().then().extract().body().jsonPath().getList(".", TodoDto.class);
        list.forEach(e -> authorizedClient.delete(e.id()).then().statusCode(HttpStatus.SC_NO_CONTENT));
    }


}
