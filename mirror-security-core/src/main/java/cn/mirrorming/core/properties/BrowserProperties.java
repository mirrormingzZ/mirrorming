package cn.mirrorming.core.properties;

import lombok.Data;

/**
 * @author: mirrorming
 * @create: 2019-06-17 12:17
 **/
@Data
public class BrowserProperties {
    private String loginPage = "/signIn.html";
    private LoginType loginType = LoginType.JSON;   //登录类型：重定向 - JSON
    private int rememberMeSeconds = 3600;           //记住我的时间
}