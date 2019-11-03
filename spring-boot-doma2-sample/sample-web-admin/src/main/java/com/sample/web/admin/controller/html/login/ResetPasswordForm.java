package com.sample.web.admin.controller.html.login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Setter
@Getter
public class ResetPasswordForm implements Serializable {

    private static final long serialVersionUID = -2603586366253013510L;

    @NotEmpty
    String email;

    String token;
}
