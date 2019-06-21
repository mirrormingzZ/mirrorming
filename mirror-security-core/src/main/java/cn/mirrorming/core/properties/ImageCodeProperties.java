package cn.mirrorming.core.properties;

import lombok.Data;

/**
 * @author: mirrorming
 * @create: 2019-06-20 17:47
 **/
@Data
public class ImageCodeProperties {
    private int width = 67;
    private int height = 23;
    private int length = 4;
    private int expireIn = 60;
    private String url;
}