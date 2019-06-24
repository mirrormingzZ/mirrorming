package cn.mirrorming.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author: mirrorming
 * @create: 2019-06-23 14:51
 **/
@Data
public class QQProperties extends SocialProperties {
    private String providerId = "callback";
}