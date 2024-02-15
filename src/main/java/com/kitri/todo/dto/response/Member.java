package com.kitri.todo.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Member {
    Long id;
    String email;
    String name;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
}
