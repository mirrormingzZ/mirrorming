package cn.mirrorming.blog.web.controller;

import cn.mirrorming.blog.domain.dto.User;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: mirrorming
 * @create: 2019-06-16 12:35
 **/
@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/user")
    public List<User> query(@RequestParam(name = "username", required = false, defaultValue = "mirror") String name) {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping("/test/user1")
    public List<User> queryByEntity(User user, @PageableDefault(page = 0, size = 10, sort = "username.asc") Pageable pageable) {
        ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE);
        List<User> users = new ArrayList<>();
        return users;
    }

    @GetMapping("/test/user/{id:\\d+}")
    public User getInfo(@PathVariable String id) {
        User user = new User();
        user.setUsername("tom");
        return user;
    }
}