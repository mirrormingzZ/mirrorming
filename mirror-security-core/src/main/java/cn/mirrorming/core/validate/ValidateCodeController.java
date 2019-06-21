package cn.mirrorming.core.validate;

import cn.mirrorming.core.validate.code.ImageCode;
import cn.mirrorming.core.validate.processor.ValidateCodeProcessor;
import cn.mirrorming.core.validate.sms.SmsCode;
import cn.mirrorming.core.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author: mirrorming
 * @create: 2019-06-20 12:08
 **/
@RestController
public class ValidateCodeController {

    public static final String SESSION_IMAGE_KEY = "SESSION_IMAGE_KEY";
    public static final String SESSION_SMS_KEY = "SESSION_SMS_KEY";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

//    @GetMapping("/code/image")
//    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        //根据随机数生成图片
//        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
//        //将随机数存到session中
//        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_IMAGE_KEY, imageCode);
//        //将生成的图片写到接口的响应中
//        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
//    }
//
//    @GetMapping("/code/sms")
//    public void createSmsCode(HttpServletRequest request) throws ServletRequestBindingException {
//
//        //根据随机数生成短信验证码
//        SmsCode smsCode = (SmsCode) smsCodeGenerator.generate(new ServletWebRequest(request));
//        //将随机数存到session中
//        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_SMS_KEY, smsCode);
//        String mobile = ServletRequestUtils.getStringParameter(request, "mobile");
//        smsCodeSender.send(mobile, smsCode.getCode());
//    }

    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        validateCodeProcessors.get(type).create(new ServletWebRequest(request, response));
    }
}