package org.freda.cooper4.configs.mybatis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.freda.cooper4.configs.datasource.DataSourceConfiguration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * MyBatis Config
 *
 * Created by rally on 16-3-24.
 */
@Configuration
@EnableTransactionManagement
@AutoConfigureAfter({DataSourceConfiguration.class})
public class MyBatisConfiguration implements EnvironmentAware,TransactionManagementConfigurer
{

    private static final Log log = LogFactory.getLog(MyBatisConfiguration.class);

    private RelaxedPropertyResolver propertyResolver;

    @Resource(name = "basicDataSource")
    private DataSource basicDataSource;

    @Override
    public void setEnvironment(Environment environment)
    {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"mybatis.");
    }
    /**
     *
     * SqlSessionFactory
     *
     * @return
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean()
    {
        try
        {
            log.debug("sqlSessionFactory Configure!");

            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

            sqlSessionFactoryBean.setDataSource(basicDataSource);

            //sqlSessionFactoryBean.setTypeAliases(new Class[]{Object.class});//放入DtoClass

            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(propertyResolver.getProperty("mapperLocations")));

            return sqlSessionFactoryBean.getObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();

            log.error("SqlSessionFactory getting fail!");

            return null;
        }
    }

    /**
     *
     * SqlSessionTemplate
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory)
    {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     *
     * 注释事务
     *
     * @return
     */
    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager()
    {
        return new DataSourceTransactionManager(basicDataSource);
    }
}
