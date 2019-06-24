package cn.mirrorming.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: mirrorming
 * @create: 2019-06-17 12:17
 **/
@ConfigurationProperties(prefix = "mirror.security")
@Data
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
    private ValidateProperties code = new ValidateProperties();
    private SocialProperties social = new SocialProperties();
}