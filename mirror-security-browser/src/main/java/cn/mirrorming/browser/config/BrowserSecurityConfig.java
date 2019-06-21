package cn.mirrorming.browser.config;

import cn.mirrorming.browser.authentication.MirrorAuthenticationFailHandler;
import cn.mirrorming.browser.authentication.MirrorAuthenticationSuccessHandler;
import cn.mirrorming.core.properties.SecurityProperties;
import cn.mirrorming.core.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

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
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setFailureHandler(mirrorAuthenticationFailHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        http
                //将自定义的验证码过滤器加在用户名密码过滤器前面
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(mirrorAuthenticationSuccessHandler)
                .failureHandler(mirrorAuthenticationFailHandler)
                //记住我功能配置
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                //授权配置
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/image"
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}