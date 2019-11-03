package com.sample.web.base.filter;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.sample.web.base.WebConst.MDC_LOGIN_USER_ID;

/**
 * ログインIDをMDCに設定する
 */
@Slf4j
public class LoginUserTrackingFilter extends OncePerRequestFilter {

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();
    @Setter
    List<String> excludeUrlPatterns;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            if (!shouldNotFilter(request)) {
                MDC.put(MDC_LOGIN_USER_ID, "GUEST");
                getUserId().ifPresent(userId -> MDC.put(MDC_LOGIN_USER_ID, userId));
            }
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    protected Optional<String> getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserIdAware) {
                val loginId = ((UserIdAware) principal).getUserId();
                return Optional.of(loginId);
            }
        }

        return Optional.empty();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        val exclude = excludeUrlPatterns.stream().anyMatch(p -> pathMatcher.match(p, request.getServletPath()));

        if (exclude) {
            return true;
        }

        return false;
    }
}
