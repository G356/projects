package com.sample.web.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * Restful API 访问路径:
 * http://IP:port/{context-path}/swagger-ui.html
 * eg:http://localhost:8080/deepai/swagger-ui.html
 */
@EnableWebMvc
@EnableSwagger2
//@ComponentScan(basePackages = {"com.sample.web.admin.controller"})
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sample.web.admin.controller"))
                .paths(PathSelectors.any())
                .build();
        // .securitySchemes(securitySchemes())
        // .securityContexts(securityContexts());
    }

    ///**
    // * 配置认证模式
    // */
    //private List<ApiKey> securitySchemes() {
    //    return newArrayList(new ApiKey("Authorization", "Authorization", "header"));
    //}
    //
    ///**
    // * 配置认证上下文
    // */
    //private List<SecurityContext> securityContexts() {
    //    return newArrayList(SecurityContext.builder()
    //            .securityReferences(defaultAuth())
    //            .forPaths(PathSelectors.any())
    //            .build());
    //}
    //
    //private List<SecurityReference> defaultAuth() {
    //    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    //    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    //    authorizationScopes[0] = authorizationScope;
    //    return newArrayList(new SecurityReference("Authorization", authorizationScopes));
    //}

    /**
     * 项目信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger测试项目 RESTful APIs")
                .version("1.0")
                .build();
    }
}
