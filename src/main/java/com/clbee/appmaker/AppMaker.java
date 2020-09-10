package com.clbee.appmaker;

import static org.hibernate.cfg.AvailableSettings.*;
import static org.hibernate.cfg.AvailableSettings.DIALECT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
@EntityScan({
		"com.clbee.appmaker.dao",
		"com.clbee.appmaker.jpa" })
public class AppMaker {

	public static void main(String[] args) {
		SpringApplication.run(AppMaker.class, args);
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {

		Properties properties = new Properties();
		properties.setProperty(DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.setProperty(NON_CONTEXTUAL_LOB_CREATION, "true");
		properties.setProperty(CONNECTION_PROVIDER_DISABLES_AUTOCOMMIT, "true");

		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setPackagesToScan("com.clbee.appmaker.dao");
		sessionFactoryBean.setHibernateProperties(properties);

		return sessionFactoryBean;
	}
}
