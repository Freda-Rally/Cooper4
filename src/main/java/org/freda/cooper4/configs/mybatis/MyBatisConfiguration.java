package org.freda.cooper4.configs.mybatis;

import com.github.pagehelper.PageHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.freda.cooper4.common.support.web.MapperInterface;
import org.freda.cooper4.configs.datasource.DataSourceConfiguration;
import org.freda.cooper4.framework.datastructure.impl.BaseDto;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
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
import java.util.Properties;

/**
 *
 * MyBatis Config
 *
 * Created by rally on 16-3-24.
 */
@Configuration
@EnableTransactionManagement
@AutoConfigureAfter({DataSourceConfiguration.class})
@MapperScan(basePackageClasses = {MapperInterface.class})
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

            sqlSessionFactoryBean.setTypeAliases(new Class[]{BaseDto.class});

            PageHelper pageHelper = new PageHelper();//分页插件.

            Properties properties = new Properties();

            properties.setProperty("dialect", "mysql");//如果使用其他数据库..请修改此处.

            properties.setProperty("rowBoundsWithCount","true");

            pageHelper.setProperties(properties);

            sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});

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
     * 此事务为JDBC事务..如果为分布式..请更换为JTA的事务..
     *
     * 具体修改..请参照 spring与jta集成的方法..进行注释事务的方法..
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
