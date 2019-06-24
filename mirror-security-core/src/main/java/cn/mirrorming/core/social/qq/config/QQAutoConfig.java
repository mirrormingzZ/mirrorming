package cn.mirrorming.core.social.qq.config;

import cn.mirrorming.core.properties.QQProperties;
import cn.mirrorming.core.properties.SecurityProperties;
import cn.mirrorming.core.social.qq.connet.QQConnetionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author: mirrorming
 * @create: 2019-06-23 14:56
 **/
@Configuration
@ConditionalOnProperty(prefix = "mirror.security.social.qq", name = "app-id") //当系统中有这个配置时才生效
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnetionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }
}