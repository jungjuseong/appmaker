package com.clbee.appmaker.config;

import com.clbee.appmaker.security.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationProvider authenticationProvider;

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    public SecurityConfig(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/member/**")
            .antMatchers("/css/**")
            .antMatchers("/js/**")
            .antMatchers("/resources/**")
            .antMatchers("/images/**")
            .antMatchers("/font/**")
            .antMatchers("/_upload/_temp/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/member/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers( "/index.html").permitAll()
                .antMatchers( "/inAppJsonSerializer.html").permitAll()
                .antMatchers( "/printAnswer.html").permitAll()
                .antMatchers("/viewJsonAnswer.html").permitAll()

                .antMatchers(HttpMethod.GET, "/contents/**","/man/**","/distribute/**","my_page/**","my/**")
                    .hasAnyRole("ROLE_ADMIN_SERVICE","ROLE_INDIVIDUAL_MEMBER","ROLE_COMPANY_MEMBER","ROLE_USER")
                .antMatchers(HttpMethod.GET, "/template/**")
                    .hasAnyRole("ROLE_ADMIN_SERVICE")
            .and()
                .logout()
                .logoutSuccessUrl("/index.html")
                .invalidateHttpSession(true)
            .and()
                // 로그인 설정
                .formLogin()
                .loginPage("/index.html")
                .loginProcessingUrl("/index.html")
                .successHandler(myAuthenticationSuccessHandler)
            .and()
                    .csrf().disable()
            .exceptionHandling();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        MyAuthenticationSuccessHandler successHandler = new MyAuthenticationSuccessHandler();
//        successHandler.setDefaultTargetUrl("/index.html");
//        return successHandler;
//    }
//
//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        CustomAuthenticationFailureHandler failureHandler = new CustomAuthenticationFailureHandler();
//        failureHandler.setDefaultFailureUrl("/loginPage?error=error");
//        return failureHandler;
//    }
//
//    @Bean
//    public LogoutSuccessHandler logoutSuccessHandler() {
//        CustomLogoutSuccessHandler logoutSuccessHandler = new CustomLogoutSuccessHandler();
//        logoutSuccessHandler.setDefaultTargetUrl("/index.html");
//        return logoutSuccessHandler;
//    }
}