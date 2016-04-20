package org.freda.cooper4.configs.datasource;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Log4jFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Druid Log Filter Config
 *
 * Created by rally on 16-3-24.
 */
@Configuration
public class DruidFilterConfiguration implements EnvironmentAware
{
    private RelaxedPropertyResolver propertyResolver;

    private static final Log log = LogFactory.getLog(DruidFilterConfiguration.class);

    @Override
    public void setEnvironment(Environment environment)
    {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"druid.");
    }

    @Bean
    public Log4jFilter logFilter()
    {
        log.debug("Druid Log Filter Configure!");

        Log4jFilter logFilter = new Log4jFilter();

        logFilter.setResultSetLogEnabled(Boolean.parseBoolean(propertyResolver.getRequiredProperty("log.resultSetLogEnabled")));

        logFilter.setDataSourceLogEnabled(Boolean.parseBoolean(propertyResolver.getRequiredProperty("log.dataSourceLogEnabled")));

        return  logFilter;
    }

    @Bean
    public StatFilter statFilter()
    {
        log.debug("Druid Stat Filter Configure");

        StatFilter statFilter = new StatFilter();

        statFilter.setSlowSqlMillis(Long.parseLong(propertyResolver.getRequiredProperty("stat.slowSqlMillis")));

        statFilter.setLogSlowSql(Boolean.parseBoolean(propertyResolver.getRequiredProperty("stat.logSlowSql")));

        statFilter.setMergeSql(Boolean.parseBoolean(propertyResolver.getRequiredProperty("stat.mergeSql")));

        return statFilter;
    }

    @Bean(name = "druidFilters")
    public List<Filter> druidFilters()
    {
        List<Filter> filters = new ArrayList<Filter>();

        filters.add(logFilter());

        filters.add(statFilter());

        return filters;
    }
}
