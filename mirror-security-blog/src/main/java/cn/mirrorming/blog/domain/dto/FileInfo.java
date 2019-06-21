package cn.mirrorming.blog.domain.dto;

import lombok.Data;

/**
 * @author: mirrorming
 * @create: 2019-06-16 16:27
 **/
@Data
public class FileInfo {
    private String path;

    public FileInfo(String path) {
        this.path = path;
    }
}