package cn.mirrorming.core.validate.sms;

/**
 * @author: mirrorming
 * @create: 2019-06-21 09:18
 **/

public interface SmsCodeSender {
    void send(String mobile, String code);
}