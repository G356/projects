package com.sample.web.admin;

import com.sample.web.base.security.BaseSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // アノテーションで役割、権限チェックを行うために定義する
@Configuration
public class SecurityConfig extends BaseSecurityConfig {

}
