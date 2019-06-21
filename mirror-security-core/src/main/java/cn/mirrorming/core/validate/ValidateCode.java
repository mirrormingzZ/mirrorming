package cn.mirrorming.core.validate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author: mirrorming
 * @create: 2019-06-20 12:01
 **/
@Data
@AllArgsConstructor
public class ValidateCode {

    private String code;

    private LocalDateTime expireTime;

    /**
     * @param code
     * @param expireIn 过期时间（秒）
     */
    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    /**
     * 判断验证码是否过期
     *
     * @return
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}