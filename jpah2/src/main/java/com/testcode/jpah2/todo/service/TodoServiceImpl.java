package com.testcode.jpah2.todo.service;

import com.testcode.jpah2.todo.domain.Todo;
import com.testcode.jpah2.todo.dto.RequestTodoDto;
import com.testcode.jpah2.todo.dto.ResponseTodoDto;
import com.testcode.jpah2.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<ResponseTodoDto> findAllTodo() {
        List<ResponseTodoDto> findTodos = todoRepository.findAll().stream().map(todo -> new ResponseTodoDto(
                todo.getId(),
                todo.getTitle(),
                todo.getDate(),
                todo.getIsFinish()
            )).collect(Collectors.toList());

        return findTodos;
    }

    @Override
    public ResponseTodoDto findTodoById(Long id) {
        return todoRepository.findById(id).map(todo -> new ResponseTodoDto(
                todo.getId(),
                todo.getTitle(),
                todo.getDate(),
                todo.getIsFinish()
        )).orElse(new ResponseTodoDto());
    }

    @Override
    public void insertTodo(RequestTodoDto requestTodoDto) {
        todoRepository.save(Todo
                .builder()
                .title(requestTodoDto.getTitle())
                .build());
    }

    @Override
    public ResponseTodoDto updateTodo(RequestTodoDto requestTodoDto) {
        Optional<Todo> findTodo = todoRepository.findById(requestTodoDto.getId());

        if (!findTodo.isPresent()) {
            return new ResponseTodoDto();
        } else {
            findTodo.get().updateTodo(requestTodoDto);
            return new ResponseTodoDto(findTodo.get());
        }
    }

    @Override
    public void removeTodoById(Long id) {
        Optional<Todo> findTodo = todoRepository.findById(id);
        if (findTodo.isPresent()) {
            todoRepository.deleteById(id);
        }
    }


}
