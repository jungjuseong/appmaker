package com.clbee.appmaker.repo;

import com.clbee.appmaker.model.Member;

import java.util.List;

public interface MemberRepo
{
	List<Member> findAll();
	Member findById(long id);
	Member save(Member member);

	void addMember( Member m );
	int updateMemberInfo( Member updatedVO, int userNum );
	int selectItselfForExisting( String DBName, String itSelf );
	List<Member> getListMember( int startNo, int companySeq, int MaxResult, String searchType, String searchValue, String isAvailable, boolean isMember);
	int getListMemberCount( int companySeq, String searchType, String searchValue, String isAvailable, boolean isMember );
	List<Member> logInVerify( String username, String password );
	Member findByUserName(String username);
	Member findByCustomInfo(String DBName, String value);
	Member findByCustomInfo(String DBName, int value);
	Member selectMemberSuccessYn(Member Member);
	Integer selectMemberCount(Member Member);
	Member selectMemberSuccessYn_(Member Member);
	Integer selectMemberCount_(Member Member);
	void updateMemberPw(Member Member);
	int selectCountWithPermisionUserByCompanySeq( int companySeq);
	int selectCountByCompanySeq(int companySeq);
	Member findCompanyMemberIdByCompanySeqAndUserGb( int companySeq );
	List<Member> getUserList( int companySeq, String[] useS, String searchValue, String searchType );
	List<Member> getPermitList ( int companySeq, String[] useS);

	//20180213 - lsy : user delete
	int deleteMemberInfo(int userNum);

	//20180619 - lsy : when app request(Library), load inapp info
	int getCompanySeqForInappContentCopy(String DBName, int userSeq);

	String selectCompanyName(String DBName, int company_seq);
}