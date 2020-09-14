package com.clbee.appmaker.config;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	@Autowired
	private LocaleResolver localeResolver;

	@Bean
	public JavaMailSender javaMailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		var host = messageSource.getMessage("send.email.domain", null, new Locale("ko"));
		var username = messageSource.getMessage("send.email.ID", null, new Locale("ko"));
		var password = messageSource.getMessage("send.email.PW", null, new Locale("ko"));

		mailSender.setHost(host);
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		mailSender.setJavaMailProperties(props);

		return mailSender;
	}
}