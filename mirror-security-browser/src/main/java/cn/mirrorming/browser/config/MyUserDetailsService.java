package cn.mirrorming.browser.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author: mirrorming
 * @create: 2019-06-17 11:16
 **/
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("MyUserDetailsService - loadUserByUsername" + username);
        return new User(username, passwordEncoder.encode("123456"), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}