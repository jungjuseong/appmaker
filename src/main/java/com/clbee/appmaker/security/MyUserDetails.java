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

	public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, boolean isEnabled) {
		super();

		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.isEnabled = isEnabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
}
