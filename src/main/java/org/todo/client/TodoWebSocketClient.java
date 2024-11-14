package org.todo.client;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.todo.specification.todo.TodoWebSocketSpecification;

import java.util.ArrayDeque;
import java.util.Queue;

@Slf4j
public class TodoWebSocketClient extends WebSocketClient {

    public Queue<String> queue = new ArrayDeque<>();

    public TodoWebSocketClient(TodoWebSocketSpecification specification) {
        super(specification.getUri());
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        log.info("Connection open");
    }

    @Override
    public void onMessage(String message) {
        log.info("Received message : " + message);
        queue.add(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("Connection closed with " + code + " and reason " + reason);
    }

    @Override
    public void onError(Exception ex) {
        log.error("WebSocket error");
        ex.printStackTrace();
    }

    public void connect() {
        Thread thread = new Thread(this);
        thread.start();
    }
}