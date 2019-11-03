package com.sample.web.base.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import static com.sample.web.base.WebConst.API_BASE_URL;

/**
 * 基底APIセキュリティコンフィグ
 */
public abstract class BaseApiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher(API_BASE_URL)
                // すべてのリクエストに認証をかける
                .authorizeRequests().anyRequest().authenticated()
                // Basic認証をかける
                .and().httpBasic().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                // CSRFチェックをしない
                .and().csrf().disable();
    }
}
