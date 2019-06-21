package cn.mirrorming.core.validate;

import cn.mirrorming.core.properties.SecurityProperties;
import cn.mirrorming.core.validate.ValidateCodeGenerator;
import cn.mirrorming.core.validate.code.ImageCodeGenerator;
import cn.mirrorming.core.validate.sms.DefaultSmsSender;
import cn.mirrorming.core.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: mirrorming
 * @create: 2019-06-20 20:21
 **/
@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")  //当容器中没有名字为imageCodeGenerator的bean时会启用这个配置
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)  //当容器中没有名字为imageCodeGenerator的bean时会启用这个配置
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsSender();
    }
}