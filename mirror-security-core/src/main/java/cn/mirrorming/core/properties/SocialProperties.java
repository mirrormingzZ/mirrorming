package cn.mirrorming.core.properties;

import lombok.Data;

/**
 * @author: mirrorming
 * @create: 2019-06-23 14:54
 **/
@Data
public class SocialProperties {
    private String filterProcessesUrl = "/qqlogin";
    private QQProperties qq = new QQProperties();
}