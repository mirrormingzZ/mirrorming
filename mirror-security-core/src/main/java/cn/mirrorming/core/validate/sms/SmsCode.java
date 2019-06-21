package cn.mirrorming.core.validate.sms;

import cn.mirrorming.core.validate.ValidateCode;

import java.time.LocalDateTime;

/**
 * @author: mirrorming
 * @create: 2019-06-20 12:01
 **/
public class SmsCode extends ValidateCode {
    public SmsCode(String code, LocalDateTime expireTime) {
        super(code, expireTime);
    }

    public SmsCode(String code, int expireIn) {
        super(code, expireIn);
    }
}