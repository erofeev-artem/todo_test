package org.todo.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TodoProperties {

    public Environment environment;
    public User user;
    public WebSocket webSocket;

    @Setter
    @Getter
    @NoArgsConstructor
    public static class Environment{
        public String host;
        public int port;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class User {
        public String login;
        public String password;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class WebSocket {
        public String host;
        public String port;
    }
}
