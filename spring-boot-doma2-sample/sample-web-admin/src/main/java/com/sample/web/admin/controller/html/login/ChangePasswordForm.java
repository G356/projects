package com.sample.web.admin.controller.html.login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Setter
@Getter
public class ChangePasswordForm implements Serializable {

    private static final long serialVersionUID = -8779126247823678771L;

    @NotEmpty
    String password;

    @NotEmpty
    String passwordConfirm;

    String token;

    Boolean done;
}
