package cn.mirrorming.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: mirrorming
 * @create: 2019-06-16 12:30
 **/

@SpringBootApplication
@EnableSwagger2
@ComponentScan("cn.mirrorming")
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}