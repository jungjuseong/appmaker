package com.clbee.appmaker.dao;

import com.clbee.appmaker.model.Member;
import java.util.List;

public interface MemberDao {

	Member addMember(Member member);
	int updateMemberInfo(Member updatedVO, int userNum );
	int selectItselfForExisting(String DBName, String itSelf );
	List<Member> getListMember(int startNo, int companySeq, int MaxResult, String searchType, String searchValue, String isAvailable, boolean isMember);
	int getListMemberCount(int companySeq, String searchType, String searchValue, String isAvailable, boolean isMember );
	List<Member> logInVerify(String username, String password );
	Member findByUserName(String username);
	Member findByCustomInfo(String DBName, String value);
	Member findByCustomInfo(String DBName, int value);
	Member selectMemberSuccessYn(Member Member);
	Integer selectMemberCount(Member Member);
	Member selectMemberSuccessYn_(Member Member);
	Integer selectMemberCount_(Member Member);
	void updateMemberPw(Member Member);
	int selectCountWithPermisionUserByCompanySeq(int companySeq);
	int selectCountByCompanySeq(int companySeq);
	Member findCompanyMemberIdByCompanySeqAndUserGb(int companySeq );
	List<Member> getUserList(int companySeq, String[] useS, String searchValue, String searchType );
	List<Member> getPermitList (int companySeq, String[] useS);

	int deleteMemberInfo(int userNum);
	int getCompanySeqForInappContentCopy(String DBName, int userSeq);

	String selectCompanyName(String DBName, int company_seq);
}