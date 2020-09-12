package com.clbee.appmaker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	MyAuthDAO myAuthDao;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication arg2) throws IOException,
			ServletException {
		// TODO Auto-generated method stub
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		MyUserDetails activeUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		GrantedAuthority element = authorities.iterator().next();
		String authority = element.getAuthority();

		Map<String, String> map = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		String userAgent = map.get("user-agent");

		if(userAgent.contains("Android")){
			response.sendRedirect("/down/list.html?currentPage=1&isMobile=ADD");
			return;
		}else if( userAgent.contains("iPad") || userAgent.contains("iPhone")){
			response.sendRedirect("/down/list.html?currentPage=1&isMobile=IPHD");
			return;
		}

		if("ROLE_USER".equals(authority) || "ROLE_COMPANY_MEMBER".equals(authority) || "ROLE_INDIVIDUAL_MEMBER".equals(authority)){
			String url = myAuthDao.selectFirstUrl(activeUser.getMember().getGroupName());
			response.sendRedirect(url);
		}else{	//서비스, 비회원
			response.sendRedirect("/app/list.html?currentPage=1&appSeq=&searchValue=&isAvailable=true");
		}
	}
}
