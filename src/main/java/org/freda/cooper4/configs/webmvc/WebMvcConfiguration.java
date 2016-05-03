package org.freda.cooper4.configs.webmvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * MVC 设置
 *
 * Created by rally on 16/5/2.
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter
{
    private static final Log log = LogFactory.getLog(WebMvcConfiguration.class);

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer)
    {
        configurer.defaultContentType(MediaType.TEXT_HTML).ignoreAcceptHeader(true);
    }
}
