package cn.mirrorming.core.social.qq.connet;

import cn.mirrorming.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author: mirrorming
 * @create: 2019-06-23 13:51
 **/

public class QQConnetionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnetionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}