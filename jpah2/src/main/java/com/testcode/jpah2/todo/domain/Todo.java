package com.testcode.jpah2.todo.domain;

import com.testcode.jpah2.todo.dto.RequestTodoDto;
import com.testcode.jpah2.todo.dto.ResponseTodoDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class Todo {

    @Id
    // @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // @Column(name = "TITLE")
    String title;

    // @Column(name = "DATE")
    @CurrentTimestamp
    @UpdateTimestamp
    LocalDateTime date;

    // @Column(name = "IS_FINISH")
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    Boolean isFinish;

    protected Todo() {
    }

    public Todo(Long id, String title, LocalDateTime date, Boolean isFinish) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.isFinish = isFinish;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Boolean getFinish() {
        return isFinish;
    }

    // request 객체 -> entity 객체
    public void requestToEntity(RequestTodoDto requestTodoDto) {
        this.title = requestTodoDto.getTitle();
    }

    // entity 객체 -> response 객체
    public ResponseTodoDto entityToResponse() {
        return new ResponseTodoDto(
                this.id,
                this.title,
                this.date,
                this.getFinish()
        );
    }
}
