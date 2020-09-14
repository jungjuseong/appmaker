package com.clbee.appmaker.config;

import com.clbee.appmaker.security.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/member/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/resources/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/font/**");
        web.ignoring().antMatchers("/_upload/_temp/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                // 인증이 되어 있을 때
                .antMatchers(HttpMethod.GET, "/contents/**","/man/**","/distribute/**","mypage/**","my/**")
                .hasAnyRole("ROLE_ADMIN_SERVICE","ROLE_INDIVIDUAL_MEMBER","ROLE_COMPANY_MEMBER","ROLE_USER")
                .antMatchers(HttpMethod.GET, "/template/**")
                .hasAnyRole("ROLE_ADMIN_SERVICE")

                .antMatchers("/member/**").permitAll()

                .antMatchers("/favicon.ico").permitAll()
                .antMatchers( "/index.html").permitAll()
                .antMatchers("/member/sendIdMail.html").permitAll()
                .antMatchers("/member/validateUserId.html").permitAll()
                .antMatchers("/member/validateUser.html").permitAll()
                .antMatchers("/member/validateEmail.html").permitAll()
                .antMatchers( "/member/loginVerify.html").permitAll()

                .antMatchers( "/findid.html").permitAll()
                .antMatchers( "/findpw.html").permitAll()
                .antMatchers( "/inAppJsonSerializer.html").permitAll()
                .antMatchers( "/printAnswer.html").permitAll()
                .antMatchers("/viewJsonAnswer.html").permitAll()

            .and()
                // 로그인 설정
                .formLogin()
                .loginPage("/index.html")
                .loginProcessingUrl("/index.html")
                .successHandler(myAuthenticationSuccessHandler)

        ;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}