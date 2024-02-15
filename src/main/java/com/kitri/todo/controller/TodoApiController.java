package com.kitri.todo.controller;

import com.kitri.todo.dto.request.RequestTodo;
import com.kitri.todo.dto.response.Member;
import com.kitri.todo.dto.response.ResponseTodo;
import com.kitri.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/todos")
public class TodoApiController {
    @Autowired
    TodoService todoService;
    @GetMapping
    public ArrayList<ResponseTodo> todos(@SessionAttribute("loginMember")Member member) {
        return todoService.findAll(member.getId());
    }
    @PostMapping
    public void addTodo(@RequestBody RequestTodo todo, @SessionAttribute("loginMember")Member member) {
        todo.setMemberId(member.getId());
        todoService.addTodo(todo);
    }

    @PutMapping("/{id}")
    public void toggleCompleted(@PathVariable int id, @RequestBody RequestTodo todo) {
        todoService.toggleCompleted(todo);
    }
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
