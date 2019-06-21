package cn.mirrorming.blog.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: mirrorming
 * @create: 2019-06-16 13:42
 **/
@RestController
public class UserController {

    @GetMapping("/me")
    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/me/user")
    public Object getCurrentUserInfo(@AuthenticationPrincipal UserDetails user) {
        return user;
    }


}