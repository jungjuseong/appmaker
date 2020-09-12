package com.clbee.appmaker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.Properties;

@Configuration
public class MailSender {

	@Bean
	public ReloadableResourceBundleMessageSource messageSource(){
		ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();

		bundleMessageSource.setBasenames("classpath:context-common-new","classpath:messages");

		bundleMessageSource.setCacheSeconds(1);
		bundleMessageSource.setDefaultEncoding("UTF-8");
		return bundleMessageSource;
	}
	
	@Bean(name = "localeResolver")
	public LocaleResolver sessionLocaleResolver(){
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("ko"));
		return localeResolver;
	}
	@Bean
	public JavaMailSender javaMailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		
		mailSender.setJavaMailProperties(props);

		//mailSender.setHost(messageSource().getMessage("send.email.domain", null, new Locale("ko")));
		//mailSender.setUsername(messageSource().getMessage("send.email.ID", null, new Locale("ko")));
		//mailSender.setPassword(messageSource().getMessage("send.email.PW", null, new Locale("ko")));

		return mailSender;
	}	
	

	      

}