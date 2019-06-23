package cn.mirrorming.core.validate.sms;

import cn.mirrorming.core.properties.SecurityConstants;
import cn.mirrorming.core.validate.processor.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: mirrorming
 * @create: 2019-06-22 17:49
 **/
@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<SmsCode> {
    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, SmsCode validateCode) throws Exception {
        String mobileParameter = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);
        smsCodeSender.send(mobileParameter, validateCode.getCode());
    }
}