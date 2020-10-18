package com.clbee.appmaker.config;

import com.clbee.appmaker.mybatis.DsgRefreshableSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@EnableTransactionManagement
@Configuration
public class DatabaseConfig {
    @Autowired
    private ApplicationContext applicationContext;

    @Value("${spring.datasource.driver-class-name}")
    private String DRIVER_CLASS;

    @Value("${spring.datasource.username}")
    private String DB_USERNAME;

    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;

    @Value("${spring.datasource.url}")
    private String JDBC_URL;

    @Value("${spring.jpa.hibernate.dialect}")
    private String DIALECT;

    @Value("${spring.jpa.hibernate.show_sql}")
    private String SHOW_SQL;

    @Value("${spring.jpa.hibernate.entity_manager.packagesToScan}")
    private String PACKAGES_TO_SCAN;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS);
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }


    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new DsgRefreshableSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/sqlmap-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/sqlmap/**/*.xml"));

        return sqlSessionFactoryBean; //.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name="entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory() {

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", DIALECT);
        hibernateProperties.put("hibernate.show_sql", SHOW_SQL);

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
        sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:hibernate.cfg.xml"));
        sessionFactoryBean.setHibernateProperties(hibernateProperties);

        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }


}