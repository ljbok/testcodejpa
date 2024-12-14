package com.testcode.jpah2.todo.domain;

import com.testcode.jpah2.todo.dto.RequestTodoDto;
import com.testcode.jpah2.todo.dto.ResponseTodoDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@DynamicInsert
public class Todo {

    @Id
    // @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "TITLE")
    private String title;

    // @Column(name = "DATE")
    @CurrentTimestamp
    // @UpdateTimestamp
    private LocalDateTime date;

    // @Column(name = "IS_FINISH")
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isFinish;

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

    public Boolean getIsFinish() {
        return isFinish;
    }


    // builder 직접 생성 -------------------------------------
    // 빌더 클래스
    public static class Builder {
        private Long id;
        private String title;
        private LocalDateTime date;
        private Boolean isFinish;

        // Builder 메서드들
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder date(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Builder isFinish(Boolean isFinish) {
            this.isFinish = isFinish;
            return this;
        }

        // 빌더 객체를 사용하여 User 객체 생성
        public Todo build() {
            return new Todo(id, title, date, isFinish);
        }
    }

    // 빌더를 반환하는 정적 메서드
    public static Builder builder() {
        return new Builder();
    }
    // ------------------------------------------ builder 직접 생성

    public void updateTodo(RequestTodoDto requestTodoDto) {
        this.id = requestTodoDto.getId();
        this.title = requestTodoDto.getTitle();
        this.date = LocalDateTime.now();
        this.isFinish = false;
    }
}
