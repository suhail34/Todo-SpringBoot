package com.practise.Todo.controller;

import com.practise.Todo.entity.Todo;
import com.practise.Todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        super();
        this.todoService = todoService;
    }

    @PostMapping()
    public ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) {
        return new ResponseEntity<Todo>(todoService.saveTodo(todo), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Todo>> getAllTodo() {
        return new ResponseEntity<List<Todo>>(todoService.getAllTodo(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable("id") int todoId) {
        return new ResponseEntity<Todo>(todoService.getTodo(todoId), HttpStatus.FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo, @PathVariable("id") int todoId) {
        return new ResponseEntity<Todo>(todoService.updateTodo(todo, todoId), HttpStatus.FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable("id") int todoId) {
        return new ResponseEntity<Todo>(todoService.deleteTodo(todoId), HttpStatus.OK);
    }
    @DeleteMapping()
    public ResponseEntity<List<Todo>> deleteTodos(@RequestBody Object object) {
        return new ResponseEntity<List<Todo>>(todoService.deleteTodos(object), HttpStatus.OK);
    }
}
