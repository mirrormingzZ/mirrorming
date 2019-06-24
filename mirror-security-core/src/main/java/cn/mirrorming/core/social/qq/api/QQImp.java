package cn.mirrorming.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @author: mirrorming
 * @create: 2019-06-23 12:03
 **/


@Slf4j
public class QQImp extends AbstractOAuth2ApiBinding implements QQ {

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    /**
     * 父类 AbstractOAuth2ApiBinding 中有 access_token 的处理
     */
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImp(String accessToken, String appId) {
        //默认access_token是加在请求头参数里面，此处改为查询参数去获取
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        log.info("请求URL_GET_OPENID链接的返回结果为：{}", result);
        //返回结果为：callback( {"client_id":"101643825","openid":"288F4AF3282A1B6D133A28702AFEBB1D"} );
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
        log.info("openId为：{}", openId);
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        log.info("请求URL_GET_USERINFO链接的返回结果为：{}", result);

        QQUserInfo qqUserInfo = null;
        try {
            qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
            qqUserInfo.setOpenId(openId);
            return qqUserInfo;
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败");
        }
    }
}