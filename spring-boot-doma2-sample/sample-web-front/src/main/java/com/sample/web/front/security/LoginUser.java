package com.sample.web.front.security;

import com.sample.domain.dto.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Setter
@Getter
public class LoginUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = -5891919297179603893L;

    /**
     * コンストラクタ
     *
     * @param user
     * @param authorities
     */
    public LoginUser(User user, Collection<? extends GrantedAuthority> authorities) {
        super(String.valueOf(user.getEmail()), user.getPassword(), authorities);
    }
}
