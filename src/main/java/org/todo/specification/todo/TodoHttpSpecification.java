package org.todo.specification.todo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.todo.properties.TodoProperties;
import org.todo.specification.Specification;

import static io.restassured.RestAssured.given;

public class TodoHttpSpecification extends Specification<TodoProperties> {

    private final String appJson = "application/json";

    public TodoHttpSpecification() {
        super(TodoProperties.class, "/todo_properties.yaml");
    }

    public RequestSpecification getRequestSpecification(boolean authorized) {

        TodoProperties.Environment environment = properties.getEnvironment();
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder
                .addHeader("Content-Type", appJson)
                .setBaseUri(environment.getHost())
                .setPort(environment.getPort())
                .addHeader("Accept", appJson)
                .log(LogDetail.ALL);
        if (authorized) {
            return given(builder.build())
                    .auth()
                    .preemptive()
                    .basic(properties.getUser().getLogin(), properties.getUser().getPassword());
        } else {
            return given(builder.build());
        }
    }
}
