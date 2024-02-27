package com.kitri.todo.service;

import com.kitri.todo.dto.request.RequestTodo;
import com.kitri.todo.dto.response.ResponseTodo;
import com.kitri.todo.exception.TodoNotFoundException;
import com.kitri.todo.mappers.TodoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ArrayList<ResponseTodo> findAll(Long id) {
        long startTime = System.currentTimeMillis(); // Record the start time

        ArrayList<ResponseTodo> todos = (ArrayList<ResponseTodo>) redisTemplate.opsForList().range("todos", 0, -1);
        if (todos == null || todos.isEmpty()) {
            todos = todoMapper.findAll(id);
            if (todos != null && !todos.isEmpty()) {
                redisTemplate.opsForList().rightPushAll("todos", todos.toArray(new ResponseTodo[0]));
            } else {
                throw new TodoNotFoundException("No todos found");
            }
        }

        long endTime = System.currentTimeMillis(); // Record the end time
        long executionTime = endTime - startTime; // Calculate the execution time

        ResponseTodo time = new ResponseTodo();
        time.setId((long)(Math.random() * 100000));
        time.setContent(String.valueOf(executionTime));
        time.setDone(false);
        todos.add(time);
        log.info("Execution time: {} ms", executionTime); // Print the execution time

        return todos;
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
