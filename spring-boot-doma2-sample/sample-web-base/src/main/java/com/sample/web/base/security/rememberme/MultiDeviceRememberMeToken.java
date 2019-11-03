package com.sample.web.base.security.rememberme;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class MultiDeviceRememberMeToken {

    private String username;

    private String remoteAddress;

    private String userAgent;

    private String series;

    private String tokenValue;

    private LocalDateTime lastUsed;
}
