package org.freda.cooper4.framework.web.filter;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *
 * 字符集过滤器
 *
 * Created by rally on 16-4-12.
 */
@Configuration
@WebFilter(filterName = "setCharacterEncodingFilter" , urlPatterns = "*.freda")
public class SetCharacterEncodingFilter implements Filter
{
    protected String encoding = null;

    protected FilterConfig filterConfig = null;

    @Override
    public void destroy()
    {
        this.encoding = null;

        this.filterConfig = null;
    }

    /**
     *设置接收和响应编码
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException
    {
        String encoding = selectEncoding(request);

        if (encoding != null)
        {
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    /**
     *初始化参数
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

        this.filterConfig = filterConfig;

        this.encoding = filterConfig.getInitParameter("encoding");
    }

    protected String selectEncoding(ServletRequest request)
    {
        return (this.encoding);
    }
}
