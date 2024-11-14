package org.todo.client;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static org.todo.specification.SpecificationFactory.getTodoRequestSpecification;

public class TodoClient {

    private final boolean isAuthorized;
    private final String path = "/todos";

    public TodoClient(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public Response get() {
        return getTodoRequestSpecification(isAuthorized).get(path);
    }

    public Response getWithOffsetAndLimit(long offset, long limit) {
        Map<String, Long> params = Map.of("offset", offset, "limit", limit);
        Header header = new Header("Accept", "text/plain");
        return getTodoRequestSpecification(isAuthorized).header(header).params(params).get(path);
    }

    public Response getWithOffset(long offset) {
        Map<String, Long> params = Map.of("offset", offset);
        Header header = new Header("Accept", "text/plain");
        return getTodoRequestSpecification(isAuthorized).header(header).params(params).get(path);
    }

    public Response getWithLimit(long limit) {
        Map<String, Long> params = Map.of("limit", limit);
        Header header = new Header("Accept", "text/plain");
        return getTodoRequestSpecification(isAuthorized).header(header).params(params).get(path);
    }

    public Response post(Object body) {
        return getTodoRequestSpecification(isAuthorized).body(body).post(path);
    }

    public Response put(Object body, long id) {
        Map<String, Long> param = Map.of("id", id);
        return getTodoRequestSpecification(isAuthorized).body(body).put(path + "/{id}", param);
    }

    public Response delete(long id) {
        return getTodoRequestSpecification(isAuthorized).delete(path + "/" + id);
    }

    private void clearReqSpec(RequestSpecification reqSpec) {
//        reqSpec.
//        new RequestSpecificationImpl()
    }
}
