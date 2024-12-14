package com.testcode.jpah2.todo.dto;

import java.time.LocalDateTime;

public class RequestTodoDto {
    long id;
    String title;
    LocalDateTime date;
    boolean isFinish;

    public RequestTodoDto() {
    }

    public RequestTodoDto(long id, String title, LocalDateTime date, boolean isFinish) {
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

    public boolean getIsFinish() {
        return isFinish;
    }
}
