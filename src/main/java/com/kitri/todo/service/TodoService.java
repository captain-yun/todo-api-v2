package com.kitri.todo.service;

import com.kitri.todo.dto.request.RequestTodo;
import com.kitri.todo.dto.response.ResponseTodo;
import com.kitri.todo.exception.TodoNotFoundException;
import com.kitri.todo.mappers.TodoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Slf4j
@Service
public class TodoService {
    @Autowired
    TodoMapper todoMapper;
    @Autowired
    private RedisTemplate<String, ResponseTodo> redisTemplate;

    // please fix this method to use redis with @Cacheable annotation
    // and print the execution time of the method
    @Cacheable(value = "todos", key = "#id")
    public ArrayList<ResponseTodo> findAll(Long id) {
        long startTime = System.currentTimeMillis(); // Record the start time
        ArrayList<ResponseTodo> todos = todoMapper.findAll(id);
        if (todos == null || todos.isEmpty()) {
            throw new TodoNotFoundException("No todos found");
        }

        long endTime = System.currentTimeMillis(); // Record the end time
        long executionTime = endTime - startTime; // Calculate the execution time

        log.info("Execution time: {} ms", executionTime); // Print the execution time

        return todos;
    }

    @CacheEvict(value = "todos", allEntries = true)
    public void addTodo(RequestTodo todo) {
        todoMapper.save(todo);
    }

    @CacheEvict(value = "todos", allEntries = true)
    public void toggleCompleted(RequestTodo todo) {
        todoMapper.update(todo);
    }

    @CacheEvict(value = "todos", allEntries = true)
    public void deleteTodo(Long id) {
        todoMapper.deleteById(id);
    }
}
