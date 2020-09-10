package com.clbee.appmaker.repo;

import com.clbee.appmaker.dao.Member;

import java.util.List;

public interface MemberRepo
{
	List<Member> findAll();
	Member findById(long id);
	Member save(Member member);
	Member findByUserName(String username);
}