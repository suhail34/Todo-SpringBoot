package com.practise.Todo.service;

import com.practise.Todo.entity.Todo;

import java.util.List;

public interface TodoService {
    Todo saveTodo(Todo todo);
    List<Todo> getAllTodo();
    Todo getTodo(int id);
    Todo updateTodo(Todo todo, int id);
    Todo deleteTodo(int id);
    List<Todo> deleteTodos(Object object);
}
