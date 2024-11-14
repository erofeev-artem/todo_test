import com.google.gson.Gson;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.glassfish.tyrus.core.wsadl.model.Application;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.todo.client.TodoClient;
import org.todo.client.TodoWebSocketClient;
import org.todo.dto.TodoDto;
import org.todo.dto.WsTodoResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.todo.specification.SpecificationFactory.getTodoRequestSpecification;
import static org.todo.specification.SpecificationFactory.getTodoWebSocketSpecification;
import static org.todo.specification.response.ResponseSpecifications.getResponse;

public class TodoCreationTest {

    static TodoClient client;
    static TodoWebSocketClient webSocketClient;

    @BeforeAll
    static void before() {
        client = new TodoClient(getTodoRequestSpecification(false));
        webSocketClient = new TodoWebSocketClient(getTodoWebSocketSpecification());
//        webSocketClient.connect();
    }




    @Test
    void getTodoTest() {
        TodoDto dto = new TodoDto(99, "buy sugar", false);
        client.post(dto).then().statusCode(HttpStatus.SC_CREATED);
        client.get().then().spec(getResponse(dto,1, 200));
//        client.get().then().spec(getResponse(id, text, completed, 1, HttpStatus.SC_OK));
    }



}
