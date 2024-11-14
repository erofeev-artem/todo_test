package org.todo.dto;

public record TodoRequestDto(long id, String text, boolean completed){}
