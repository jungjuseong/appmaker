package com.clbee.appmaker.service;

import com.clbee.appmaker.model.list.MemberList;
import com.clbee.appmaker.model.Member;
import com.clbee.appmaker.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public interface MemberService {

	public void addMember(Member m );
	public int verifyIfExists(String fieldName, String itSelf);
	public int verifyLogin(String username, String password);
	public Member findByUserName(String username);

	public Member findByCustomInfo(String DBName, String value);
	public int updateMemberInfo(Member m, int userNum);
	public Member selectMemberSuccessYn(Member member);
	@Transactional
	public int selectMemberCount(Member member);

	@Transactional
	public Member selectMemberSuccessYn_(Member member);

	@Transactional
	public int selectMemberCount_(Member member) ;
	@Transactional
	public void updateMemberPw(Member member);

	@Transactional
	public MemberList getListMember(int currentPage, int companySeq, int maxResult, String searchType, String searchValue, String isAvailable, boolean isMember ) ;
	@Transactional
	public Member findByCustomInfo(String DBName, int value);

	@Transactional
	public String findCompanyMemberIdByCompanySeqAndUserGb(int companySeq) ;
	@Transactional
	public int selectCountWithPermisionUserByCompanySeq(int companySeq);
	
	@Transactional
	public List<Member> getUserList(int companySeq, String[] useS, String searchValue, String searchType  );

	@Transactional
	public List<Member> getPermitList(int companySeq, String[] useS);
	@Transactional
	public int deleteMemberInfo(int value);

	@Transactional
	public int getCompanySeqForInAppContentCopy(int userSeq);

	@Transactional
	public String selectCompanyName(int company_seq);
}