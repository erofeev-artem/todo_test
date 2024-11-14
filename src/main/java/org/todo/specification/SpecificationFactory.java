package org.todo.specification;

import io.restassured.specification.RequestSpecification;
import org.todo.specification.todo.TodoHttpSpecification;
import org.todo.specification.todo.TodoWebSocketSpecification;

public class SpecificationFactory {
    public static RequestSpecification getTodoRequestSpecification(boolean authorized) {
        return new TodoHttpSpecification().getRequestSpecification(authorized);
    }

    public static TodoWebSocketSpecification getTodoWebSocketSpecification() {
        return new TodoWebSocketSpecification();
    }

}
