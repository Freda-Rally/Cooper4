package org.freda.cooper4.configs.datasource;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * DataSource Config
 *
 * Created by rally on 16-3-24.
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration implements EnvironmentAware
{
    private RelaxedPropertyResolver propertyResolver;

    private static final Log log = LogFactory.getLog(DataSourceConfiguration.class);

    @Resource(name = "druidFilters")
    private List<Filter> filterList;

    @Override
    public void setEnvironment(Environment environment)
    {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"jdbc.");
    }

    /**
     * DataSource
     * @return DataSource
     */
    @Primary
    @Bean(name = "basicDataSource", destroyMethod = "close", initMethod = "init")
    public DataSource basicDataSource() throws SQLException
    {
        log.debug("Basic DataSource Configure!");

        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(propertyResolver.getProperty("url"));

        dataSource.setDriverClassName(propertyResolver.getProperty("driverClass"));

        dataSource.setUsername(propertyResolver.getProperty("username"));

        dataSource.setPassword(propertyResolver.getProperty("password"));

        dataSource.setInitialSize(Integer.parseInt(propertyResolver.getRequiredProperty("pool.initialSize")));

        dataSource.setMaxActive(Integer.parseInt(propertyResolver.getRequiredProperty("pool.maxActive")));

        dataSource.setMaxWait(Long.parseLong(propertyResolver.getRequiredProperty("pool.maxWait")));

        dataSource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getRequiredProperty("pool.testWhileIdle")));

        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(propertyResolver.getRequiredProperty("pool.timeBetweenEvictionRunsMillis")));

        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(propertyResolver.getRequiredProperty("pool.minEvictableIdleTimeMillis")));

        dataSource.setRemoveAbandoned(Boolean.parseBoolean(propertyResolver.getRequiredProperty("pool.removeAbandoned")));

        dataSource.setRemoveAbandonedTimeout(Integer.parseInt(propertyResolver.getRequiredProperty("pool.removeAbandonedTimeout")));

        dataSource.setLogAbandoned(Boolean.parseBoolean(propertyResolver.getRequiredProperty("pool.logAbandoned")));

        dataSource.setPoolPreparedStatements(Boolean.parseBoolean(propertyResolver.getRequiredProperty("pool.poolPreparedStatements")));

        dataSource.setProxyFilters(filterList);

        return dataSource;
    }
}
