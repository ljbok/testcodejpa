package com.testcode.jpah2.todo.dto;

import com.testcode.jpah2.todo.domain.Todo;

import java.time.LocalDateTime;

public class ResponseTodoDto {
    long id;
    String title;
    LocalDateTime date;
    boolean isFinish;

    public ResponseTodoDto() {
    }

    public ResponseTodoDto(long id, String title, LocalDateTime date, boolean isFinish) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.isFinish = isFinish;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean getIsFinish() { return isFinish; }

    public ResponseTodoDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.date = todo.getDate();
        this.isFinish = todo.getIsFinish();
    }
}
