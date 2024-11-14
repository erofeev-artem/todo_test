import com.google.gson.Gson;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.todo.client.TodoClient;
import org.todo.client.TodoWebSocketClient;
import org.todo.dto.TodoDto;
import org.todo.dto.WsTodoResponseDto;

import java.util.Queue;

import static org.todo.specification.SpecificationFactory.getTodoWebSocketSpecification;

public class TodoWebSocketTest extends BaseTest {

    private TodoClient client;
    private TodoWebSocketClient webSocketClient;

    @BeforeEach
    void before() {
        client = new TodoClient(false);
        webSocketClient = new TodoWebSocketClient(getTodoWebSocketSpecification());
    }

    @Test
    void websocketTest() {
        Gson gson = new Gson();
        String newTodo = "new_todo";
        webSocketClient.connect();
        TodoDto dto = new TodoDto(77, "play a game", false);
        TodoDto dto2 = new TodoDto(78, "read a book", true);
        client.post(dto).then().statusCode(HttpStatus.SC_CREATED);
        client.post(dto2).then().statusCode(HttpStatus.SC_CREATED);
        Queue<String> queue = webSocketClient.queue;
        Assertions.assertEquals(2, queue.size());
        Assertions.assertEquals(gson.fromJson(queue.peek(), WsTodoResponseDto.class).data(), dto);
        Assertions.assertEquals(gson.fromJson(queue.poll(), WsTodoResponseDto.class).type(), newTodo);
        Assertions.assertEquals(gson.fromJson(queue.peek(), WsTodoResponseDto.class).data(), dto2);
        Assertions.assertEquals(gson.fromJson(queue.poll(), WsTodoResponseDto.class).type(), newTodo);
    }
}
