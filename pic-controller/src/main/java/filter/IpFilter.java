package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 指定Ip过滤器
 * @author yxl17
 * @Package : org.example.filter
 * @Create on : 2023/11/11 19:26
 **/


@WebFilter(filterName = "IpFilter", urlPatterns = "/*")
public class IpFilter implements Filter {
    private String url;

    @Override
    public void init(FilterConfig filterConfig) {
        this.url = filterConfig.getInitParameter("org/example/*");
        System.out.println("过滤器的初始化方法！URL=" + this.url + "，开始访问.........");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("那就浅浅过滤一下吧");
        //进入下层业务
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("程序关闭或者主动调用了关闭filter方法");
    }
}
