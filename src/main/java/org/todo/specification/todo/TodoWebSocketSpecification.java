package org.todo.specification.todo;

import lombok.Getter;
import org.todo.properties.TodoProperties;
import org.todo.specification.Specification;

import java.net.URI;

@Getter
public class TodoWebSocketSpecification extends Specification<TodoProperties> {

    private String path = "/ws";

    public TodoWebSocketSpecification() {
        super(TodoProperties.class, "/todo_properties.yaml");
    }

    public URI getUri() {
        return URI.create(properties.webSocket.getHost() + ":" + properties.environment.getPort() + path);
    }

}
