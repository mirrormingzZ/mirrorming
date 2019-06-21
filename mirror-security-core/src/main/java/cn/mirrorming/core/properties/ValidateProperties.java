package cn.mirrorming.core.properties;

import lombok.Data;

/**
 * @author: mirrorming
 * @create: 2019-06-20 17:51
 **/
@Data
public class ValidateProperties {
    private ImageCodeProperties image = new ImageCodeProperties();
    private SmsCodeProperties sms = new SmsCodeProperties();
}