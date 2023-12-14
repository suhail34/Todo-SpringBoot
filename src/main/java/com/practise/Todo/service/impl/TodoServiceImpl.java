package com.practise.Todo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practise.Todo.entity.Todo;
import com.practise.Todo.exception.ResourceNotFoundException;
import com.practise.Todo.repository.TodoRepository;
import com.practise.Todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        super();
        this.todoRepository = todoRepository;
    }

    @Override
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodo(int id) {
//        Optional<Todo> todo = todoRepository.findById(id);
//        if(todo.isPresent()){
//            return todo.get();
//        }
//        throw new ResourceNotFoundException("Todo", "ID", id);
        return todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo", "ID", id));
    }

    @Override
    public Todo updateTodo(Todo todo, int id) {
        Todo existingTodo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo", "ID", id));
        existingTodo.setUserName(todo.getUserName());
        existingTodo.setText(todo.getText());
        existingTodo.setCompleted(todo.isCompleted());
        todoRepository.save(existingTodo);
        return existingTodo;
    }

    @Override
    public Todo deleteTodo(int id) {
        Todo existingTodo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo", "ID", id));
        todoRepository.delete(existingTodo);
        return existingTodo;
    }

    @Override
    public List<Todo> deleteTodos(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Todo> todos = new ArrayList<>();
        try {
            Map<String, List<Integer>> requestMap = objectMapper.readValue(objectMapper.writeValueAsString(object), Map.class);
            List<Integer> values = requestMap.get("ids");
            todos = todoRepository.findAllById(values);
            todoRepository.deleteAllById(values);
            return todos;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return todos;
        }
    }
}
