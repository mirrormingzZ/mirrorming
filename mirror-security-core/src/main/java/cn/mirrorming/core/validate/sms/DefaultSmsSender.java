package cn.mirrorming.core.validate.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: mirrorming
 * @create: 2019-06-21 09:20
 **/
@Component
@Slf4j
public class DefaultSmsSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        log.info("向手机号：{}，发送验证码：{}", mobile, code);
    }
}