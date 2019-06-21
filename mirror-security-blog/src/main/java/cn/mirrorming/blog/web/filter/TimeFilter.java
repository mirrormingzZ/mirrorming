/**
 *
 */
package cn.mirrorming.blog.web.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

//@Component //在WebConfig中已经配置
@Slf4j
public class TimeFilter implements Filter {


    @Override
    public void destroy() {
        log.info("[TimeFilter] destroy");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("[TimeFilter] start");
        long start = new Date().getTime();
        chain.doFilter(request, response);
        log.info("[TimeFilter] 耗时:" + (new Date().getTime() - start));
        log.info("[TimeFilter] finish");
    }


    @Override
    public void init(FilterConfig arg0) throws ServletException {
        log.info("[TimeFilter] init");
    }

}
