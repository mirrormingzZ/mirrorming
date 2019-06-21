package cn.mirrorming.core.validate.sms;

import cn.mirrorming.core.properties.SecurityProperties;
import cn.mirrorming.core.validate.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: mirrorming
 * @create: 2019-06-20 20:01
 **/
@Component("smsCodeGenerator")
@Data
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public SmsCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new SmsCode(code, securityProperties.getCode().getSms().getLength());
    }
}