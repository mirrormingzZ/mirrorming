package cn.mirrorming.browser.config;

import cn.mirrorming.browser.authentication.MirrorAuthenticationFailHandler;
import cn.mirrorming.browser.authentication.MirrorAuthenticationSuccessHandler;
import cn.mirrorming.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import cn.mirrorming.core.properties.SecurityConstants;
import cn.mirrorming.core.properties.SecurityProperties;
import cn.mirrorming.core.validate.MirrorValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author: mirrorming
 * @create: 2019-06-16 19:50
 **/
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MirrorAuthenticationSuccessHandler mirrorAuthenticationSuccessHandler;

    @Autowired
    private MirrorAuthenticationFailHandler mirrorAuthenticationFailHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MirrorValidateCodeSecurityConfig mirrorValidateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer mirrorSocialSecurityConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);//启动的时候创建数据库表(仅第一次启动时用，后面数据库有表就会报错）
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                /**
                 * ================表单登录功能配置================
                 */
                .formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(mirrorAuthenticationSuccessHandler)
                .failureHandler(mirrorAuthenticationFailHandler)
                .and()
                /**
                 * ==================验证码配置====================
                 */
                .apply(mirrorValidateCodeSecurityConfig)
                .and()
                /**
                 * =================验证码登录配置=================
                 */
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                /**
                 * ==============SpringSocial配置================
                 */
                .apply(mirrorSocialSecurityConfig)
                .and()
                /**
                 * =================记住我功能配置=================
                 */
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                /**
                 * ===================授权配置=====================
                 */
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}