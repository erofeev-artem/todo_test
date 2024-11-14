package org.todo.specification;

import io.restassured.specification.RequestSpecification;
import org.todo.properties.PropertiesConverter;
import org.todo.properties.TodoProperties;
import org.todo.specification.todo.HttpSpecification;

public class RequestSpecificationFactory {
    public static RequestSpecification getTodoRequestSpecification(boolean authorized) {
        return new HttpSpecification().getRequestSpecification(authorized);
    }

}
