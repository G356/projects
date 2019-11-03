package com.huayou.samlboot2.spring.mvc;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.lang.annotation.*;
/**
 * @author
 */
@Target({ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface SAMLUser {
}
