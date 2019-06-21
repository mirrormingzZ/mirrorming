package cn.mirrorming.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: mirrorming
 * @create: 2019-06-20 20:00
 **/

public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}