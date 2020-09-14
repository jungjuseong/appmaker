package com.clbee.config;

import com.clbee.appmaker.mybatis.DsgRefreshableSqlSessionFactoryBean;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Properties;

@EnableTransactionManagement
@MapperScan(value="com.clbee.appmaker.dao")
public class HibernateConfig {

    private final ApplicationContext applicationContext;

    @Value("${spring.datasource.driver-class-name}")
    private String DRIVER_CLASS;

    @Value("${spring.datasource.username}")
    private String DB_USERNAME;

    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;

    @Value("${spring.datasource.url}")
    private String JDBC_URL;

    public HibernateConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean(name = "SessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan("com.clbee.appmaker.model");
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setConfigLocation(applicationContext.getResource("classpath:hibernate.cfg.xml"));

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource0() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS);
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl(JDBC_URL);

        Properties props = new Properties();
        props.put("autoReconnect", true);
        props.put("characterEncoding", "UTF-8");
        dataSource.setProperties(props);

        dataSource.setAcquireIncrement(1);
        dataSource.setMinPoolSize(5);
        dataSource.setMaxPoolSize(20);
        dataSource.setMaxStatements(50);
        dataSource.setIdleConnectionTestPeriod(3000);
        try {
            dataSource.setLoginTimeout(300);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setUser(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);

        return dataSource;
    }

    @Bean
    public DataSource basicDataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setMaxActive(-1);
        dataSource.setMaxIdle(-1);
        dataSource.setMaxWait(10000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestWhileIdle(true);
        dataSource.setMinEvictableIdleTimeMillis(216000000);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setNumTestsPerEvictionRun(-1);

        return dataSource;
    }
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());

    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new DsgRefreshableSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());

        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:/sql-map-config.xml"));

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:/mybatis/sqlmap/**/*.xml");
        sqlSessionFactory.setMapperLocations(resources);

        return sqlSessionFactory.getObject();
    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
    @Bean
    public SqlSessionFactory sqlSessionFactoryForReadWrite() throws Exception {

        SqlSessionFactoryBean sqlSessionFactory = new DsgRefreshableSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(basicDataSource());
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:/sql-map-config.xml"));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:/mybatis/sqlmap/**/*.xml");

        sqlSessionFactory.setMapperLocations(resources);
        return sqlSessionFactory.getObject();
    }

}