package com.testcode.jpah2.todo.service;

import com.testcode.jpah2.todo.domain.Todo;
import com.testcode.jpah2.todo.dto.ResponseTodoDto;

import java.util.List;

public interface TodoService {

    // 전체 todo 조회
    public List<ResponseTodoDto> findAllTodo();
    
    // id로 특정 todo 조회
    public ResponseTodoDto findTodoById(Long id);
    
    // todo 등록
    public void insertTodo(ResponseTodoDto todo);
    
    // todo 수정
    public ResponseTodoDto updateTodo(Todo todo);
    
    // todo 삭제
    public void removeTodoById(Long id);
}
