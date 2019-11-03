package com.sample.web.front.controller.login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Setter
@Getter
public class LoginForm implements Serializable {

    private static final long serialVersionUID = -4165914705498462325L;

    @NotEmpty
    String loginId;

    @NotEmpty
    String password;
}
