package com.testcode.jpah2.todo.controller;

import com.testcode.jpah2.todo.dto.RequestTodoDto;
import com.testcode.jpah2.todo.dto.ResponseTodoDto;
import com.testcode.jpah2.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo/")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/findAll")
    public List<ResponseTodoDto> findAllTodo() {
        return todoService.findAllTodo();
    }

    @GetMapping("/find/{todoId}")
    public ResponseTodoDto findTodo(@PathVariable("todoId") Long todoId) {
        return todoService.findTodoById(todoId);
    }

    @PostMapping("/insert")
    public void insertTodo(@RequestBody RequestTodoDto requestTodoDto) {
        todoService.insertTodo(requestTodoDto);
    }

    @PostMapping("/update")
    public ResponseTodoDto updateTodo(@RequestBody RequestTodoDto requestTodoDto) {
        return todoService.updateTodo(requestTodoDto);
    }

    @PatchMapping("/remove/{todoId}")
    public void removeTood(@PathVariable Long todoId) {
        todoService.removeTodoById(todoId);
    }
}
