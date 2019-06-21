package cn.mirrorming.blog.service.imp;

import cn.mirrorming.blog.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: mirrorming
 * @create: 2019-06-16 15:00
 **/
@Service
public class TestServiceImp implements TestService {
    @Override
    public String testMessage(String message) {
        return "hello" + message;
    }
}