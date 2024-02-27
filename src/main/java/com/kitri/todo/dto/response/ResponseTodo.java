package com.kitri.todo.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseTodo implements Serializable {
    Long id;
    String content;
    Boolean done;
    Long memberId;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
}
