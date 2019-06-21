package cn.mirrorming.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: mirrorming
 * @create: 2019-06-16 15:31
 **/
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUserNotExistException(UserNotExistException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", ex.getId());
        result.put("msg", ex.getMessage());
        return result;
    }
}