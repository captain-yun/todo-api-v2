package com.kitri.todo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLogin {
    String email;
    String password;
    Boolean rememberEmail;
}
