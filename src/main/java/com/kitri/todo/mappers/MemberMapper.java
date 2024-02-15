package com.kitri.todo.mappers;

import com.kitri.todo.dto.request.RequestSignUp;
import com.kitri.todo.dto.response.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Member findByEmail(String email);
    String findPasswordByEmail(String email);
    void save(RequestSignUp todo);
    void deleteByEmail(String email);
}
