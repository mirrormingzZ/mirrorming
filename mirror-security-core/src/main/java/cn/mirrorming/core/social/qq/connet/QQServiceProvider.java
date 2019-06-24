package cn.mirrorming.core.social.qq.connet;

import cn.mirrorming.core.social.qq.api.QQ;
import cn.mirrorming.core.social.qq.api.QQImp;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @author: mirrorming
 * @create: 2019-06-23 13:12
 **/

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImp(accessToken, appId);
    }
}