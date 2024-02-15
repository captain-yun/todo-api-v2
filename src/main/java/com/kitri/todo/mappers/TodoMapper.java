package com.kitri.todo.mappers;

import com.kitri.todo.dto.request.RequestTodo;
import com.kitri.todo.dto.response.ResponseTodo;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TodoMapper {
    ArrayList<ResponseTodo> findAll(Long id);
    ResponseTodo findById(Long id);
    void save(RequestTodo todo);
    void update(RequestTodo todo);
    void deleteById(Long id);
}
