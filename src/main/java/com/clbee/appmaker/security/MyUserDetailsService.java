package com.clbee.appmaker.security;

import com.clbee.appmaker.service.MemberService;
import com.clbee.appmaker.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority; // .GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		Member member = memberService.findByUserName(username);
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		switch(Integer.parseInt(member.getUserGb())) {
			case 63 :	//	새 권한 체계 : 사용자
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				break;
			case 127 :	//	회원(1:기업,2:개인)
				if ("1".equals(member.getCompanyGb()))
					 authorities.add(new SimpleGrantedAuthority("ROLE_COMPANY_MEMBER"));
				else
					 authorities.add(new SimpleGrantedAuthority("ROLE_INDIVIDUAL_MEMBER"));
				break;
			case 255 :	//	service
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN_SERVICE"));
				break;
		}

		if("5".equals(member.getUserStatus())){
			return new MyUserDetails(username, member.getUserPw(),authorities,false);
		}
		else if("4".equals(member.getUserStatus())){
			return new MyUserDetails(username, member.getUserPw(),authorities,true);
		}

		return null;
	}	
}
