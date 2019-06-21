package cn.mirrorming.core.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * @author: mirrorming
 * @create: 2019-06-20 15:07
 **/
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}