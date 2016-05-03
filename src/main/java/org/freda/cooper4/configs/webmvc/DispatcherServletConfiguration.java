package org.freda.cooper4.configs.webmvc;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * 修改DispatcherServlet默认配置
 *
 * Created by rally on 16-4-12.
 */
@Configuration
public class DispatcherServletConfiguration
{
    private static final String[] DISPATCHER_SERVLET_URL = {"*.freda","*.js","*.css","*.jsp","*.png","*.jpg"};

    /**
     * 修改DispatcherServlet默认配置
     *
     * @param dispatcherServlet
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet)
    {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);

        registration.getUrlMappings().clear();

        registration.addUrlMappings(DISPATCHER_SERVLET_URL);

        return registration;
    }
}
