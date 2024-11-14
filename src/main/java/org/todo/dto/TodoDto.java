package org.todo.dto;

public record TodoDto(long id, String text, boolean completed){
    public TodoDto {
    }

    public TodoDto(String text, boolean completed) {
        this(0, text, completed);
    }

    public TodoDto(long id, boolean completed) {
        this(id, null, completed);
    }

    public TodoDto(long id, String text) {
        this(id, text, false);
    }

    public TodoDto(String text) {
        this(0, text, false);
    }
}
