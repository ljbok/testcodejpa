package com.testcode.jpah2.todo.service;

import com.testcode.jpah2.todo.domain.Todo;
import com.testcode.jpah2.todo.dto.ResponseTodoDto;
import com.testcode.jpah2.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<ResponseTodoDto> findAllTodo() {
        return null;
    }

    @Override
    public ResponseTodoDto findTodoById(Long id) {
        return null;
    }

    @Override
    public void insertTodo(ResponseTodoDto todo) {

    }

    @Override
    public ResponseTodoDto updateTodo(Todo todo) {
        return null;
    }

    @Override
    public void removeTodoById(Long id) {

    }
}
