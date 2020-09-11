package com.clbee.appmaker.service;

import com.clbee.appmaker.model.MemberList;
import com.clbee.appmaker.model.Member;

import java.util.List;

public interface MemberService {
	void addMember(Member m);
	int verifyIfExists(String DBName, String itSelf);
	int logInVerify(String username, String password);
	MemberList getListMember( int currentPage, int companySeq, int maxResult, String searchType, String searchValue, String isAvailable, boolean isMember );
	Member findByUserName(String username);
	int updateMemberInfo( Member m, int userNum);
	Member findByCustomInfo(String DBName, String value);
	Member findByCustomInfo(String DBName, int value);
	Member selectMemberSuccessYn(Member member);
	Integer selectMemberCount(Member member);
	Member selectMemberSuccessYn_(Member member);
	Integer selectMemberCount_(Member member);
	void updateMemberPw(Member member);
	String findCompanyMemberIdByCompanySeqAndUserGb( int companySeq );
	int selectCountWithPermisionUserByCompanySeq( int companySeq);
	List<Member> getUserList(  int companySeq, String[] useS, String searchValue, String searchType  );
	List<Member> getPermitList( int companySeq, String[] useS );

	//20180213 - lsy : user delete
	int deleteMemberInfo(int userNum);
	
	//20180619 - lsy : when app request(Library), load inapp info
	int getCompanySeqForInappContentCopy(int userSeq);
	
	String selectCompanyName(int company_seq);
}