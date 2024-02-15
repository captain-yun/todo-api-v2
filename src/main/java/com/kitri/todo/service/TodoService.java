package com.kitri.todo.service;

import com.kitri.todo.dto.request.RequestTodo;
import com.kitri.todo.dto.response.ResponseTodo;
import com.kitri.todo.mappers.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TodoService {
    @Autowired
    TodoMapper todoMapper;

    public ArrayList<ResponseTodo> findAll(Long id) {
        return todoMapper.findAll(id);
    }

    public void addTodo(RequestTodo todo) {
        todoMapper.save(todo);
    }

    public void toggleCompleted(RequestTodo todo) {
        todoMapper.update(todo);
    }

    public void deleteTodo(Long id) {
        todoMapper.deleteById(id);
    }
}
