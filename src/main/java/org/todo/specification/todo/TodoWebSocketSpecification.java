package org.todo.specification.todo;

import lombok.Getter;
import org.todo.properties.TodoProperties;
import org.todo.specification.Specification;

import java.net.URI;

@Getter
public class SocketSpecification extends Specification<TodoProperties> {

    public SocketSpecification() {
        super(TodoProperties.class, "/todo_properties.yaml");
    }

    public URI getUri(){
        return URI.create(properties.environment.getHost());
    }
}
