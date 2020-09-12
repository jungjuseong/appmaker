package com.clbee.appmaker.configuration;

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
        //super.configure(web);

        // 스프링 시큐리티의 필터 연결 설정
        // 예외가 웹 접근 URL를 설정한다.
        // ACL(Access Control List - 접근 제어 목록)에 등록하지 않을  URL을 예외 설정
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/resources/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/font/**");
        web.ignoring().antMatchers("/_upload/_temp/images/**");

        web.ignoring().regexMatchers("\\A/favicon.ico\\Z");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            // 인증이 되어 있을 때
            .antMatchers(HttpMethod.GET, "/contents/**","/man/**","/distribute/**","/mypage/**","/my/**")
            .hasAnyRole("ROLE_ADMIN_SERVICE","ROLE_INDIVIDUAL_MEMBER","ROLE_COMPANY_MEMBER","ROLE_USER")
            .antMatchers(HttpMethod.GET, "/template/**")
            .hasAnyRole("ROLE_ADMIN_SERVICE")

            // 위에서 걸리는 것이 없으면
            .antMatchers("/*.**").permitAll()
//            .antMatchers(HttpMethod.DELETE,"/member/**")
//            .antMatchers(HttpMethod.GET, "/index.html").permitAll()
//            .antMatchers(HttpMethod.GET, "/send_id_mail.html").permitAll()
//            .antMatchers(HttpMethod.GET, "/userIdValidation.html").permitAll()
//            .antMatchers(HttpMethod.GET, "/emailValidation.html").permitAll()
//            .antMatchers(HttpMethod.GET, "/member/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/findid.html").permitAll()
//            .antMatchers(HttpMethod.GET, "/inAppJsonSerializer.html").permitAll()
//            .antMatchers(HttpMethod.GET, "/printAnswer.html").permitAll()
//            .antMatchers(HttpMethod.GET, "/viewJsonAnswer.html").permitAll()

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