package cn.mirrorming.core.validate.code;

import cn.mirrorming.core.validate.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * @author: mirrorming
 * @create: 2019-06-20 12:01
 **/
@Data
public class ImageCode extends ValidateCode {
    private BufferedImage image;

    /**
     * @param image
     * @param code
     * @param expireIn 过期时间（秒）
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }
}