package com.clbee.appmaker.dao;

import com.clbee.appmaker.model.Member;

import java.util.Date;
import java.util.List;

public interface MemberDao {

	Member addMember(Member member);
	int updateMemberInfo(Member updated, int userSeq);
	void updateMemberEmailCheckInfo(String userStatus, Date emailChkDt,String emailChkGb, String checkSession, int userSeq);
	void updateMemberLoginStatus(String loginStatus, int userSeq);
	void updateMemberUserPeriod(Date loginDt, Date userStartDt, Date userEndDt, int userSeq);
	void updateMemberUserWithdrawal(String userStatus, Date withdrawalDt, int userSeq);

	int verifyIfExists(String fieldName, String itSelf);
	List<Member> getListMember(int startNo, int companySeq, int MaxResult, String searchType, String searchValue, String isAvailable, boolean isMember);
	int getListMemberCount(int companySeq, String searchType, String searchValue, String isAvailable, boolean isMember );
	List<Member> verifyLogin(String username, String userpw );
	Member findByUserName(String username);
	Member findByCustomInfo(String DBName, String value);
	Member findByCustomInfo(String DBName, int value);
	Member selectMemberSuccessYn(Member Member);
	int selectMemberCount(Member Member);
	Member selectMemberSuccessYn_(Member Member);
	int selectMemberCount_(Member Member);
	void updateMemberPw(String userId, String userPw);
	int selectCountWithPermittedUserByCompanySeq(int companySeq);
	int selectCountByCompanySeq(int companySeq);
	Member findCompanyMemberIdByCompanySeqAndUserGb(int companySeq );
	List<Member> getUserList(int companySeq, String[] useS, String searchValue, String searchType );
	List<Member> getPermitList (int companySeq, String[] useS);

	int deleteMemberInfo(int userNum);
	int getCompanySeqForInAppContentCopy(String DBName, int userSeq);

	String selectCompanyName(String DBName, int company_seq);
}