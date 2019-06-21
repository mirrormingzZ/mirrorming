package cn.mirrorming.core.config;

import cn.mirrorming.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: mirrorming
 * @create: 2019-06-17 12:24
 **/

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class CoreSecurityConfig {
}