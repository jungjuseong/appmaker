package com.clbee.appmaker.security;

import com.clbee.appmaker.model.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = -7306690067344994732L;

	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private Member member;
	private boolean isEnabled;
	private boolean isBook;

	public MyUserDetails(String username, String password,
						 Collection<? extends GrantedAuthority> authorities,
						 Member member, boolean isEnabled, boolean isBook) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.member = member;
		this.isEnabled = isEnabled;
		this.isBook = isBook;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnabled;
	}
}
