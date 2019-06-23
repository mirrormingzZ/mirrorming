package cn.mirrorming.core.authentication.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * @author: mirrorming
 * @create: 2019-06-22 16:18
 **/
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    //存储认证信息
    private final Object principal;

    // ~ Constructors
    // ===================================================================================================

    /**
     * 任何希望创建的代码都可以安全地使用此构造函数
     * <code> UsernamePasswordAuthenticationToken </ code>，作为{@link #isAuthenticated（）}
     * 将返回<code> false </ code>。
     */
    public SmsCodeAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
        setAuthenticated(false);
    }

    /**
     * 此构造函数只能由<code> AuthenticationManager </ code>或使用
     * 满足的<code> AuthenticationProvider </ code>实现
     * 产生一个受信任的（即{@link #isAuthenticated（）} = <code> true </ code>）
     * 身份验证令牌。
     *
     * @param principal
     * @param authorities
     */
    public SmsCodeAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true); // must use super, as we override
    }

    // ~ Methods
    // ========================================================================================================


    @Override
    public Object getCredentials() {
        return null;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}