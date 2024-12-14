package com.testcode.jpah2.todo.dto;

import java.time.LocalDateTime;

public class ResponseTodoDto {
    long id;
    String title;
    LocalDateTime date;
    boolean isFinish;

    protected ResponseTodoDto() {
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

    public boolean isFinish() {
        return isFinish;
    }
}