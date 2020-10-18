package com.clbee.appmaker.service.impl;

import com.clbee.appmaker.dao.MemberDao;
import com.clbee.appmaker.model.Member;
import com.clbee.appmaker.model.list.MemberList;
import com.clbee.appmaker.service.MemberService;
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

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;

	@Transactional
	public void addMember(Member m ) {
		memberDao.addMember(m );
	}
	
	@Transactional
	public int verifyIfExists(String fieldName, String itSelf){
		return memberDao.verifyIfExists(fieldName, itSelf);
	}
	
	@Transactional
	public int verifyLogin(String username, String password ){
		List<Member> appList = memberDao.verifyLogin(username, password);

		Iterator iterableAppList = appList.iterator();
		
		if(iterableAppList.hasNext()) {
			Member member =  (Member) iterableAppList.next();
			if("1".equals(member.getUserStatus())){
				// Ż��
				return 3;
			}else if("2".equals(member.getUserStatus())) {
				// ����
				return 4;
			}else if("3".equals(member.getUserStatus())) {
				// ���� Ż��
				return 5;
			}else if("5".equals(member.getUserStatus())) {
				// ��� ���
				return 6;
			}else if("4".equals(member.getUserStatus())) {
			
				if(!"1".equals(member.getUserGb())) {
					return 1;
				}else {
					if("2".equals(member.getDateGb()) ) {
						return 1;
					}else{
						try {
							SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
							DateFormat inputDF  = new SimpleDateFormat("MM/dd/yy");
							Date date = new Date();			
							Date formattedDate;
							
							formattedDate = inputDF.parse(format.format(date));
			
			
							Calendar cal = Calendar.getInstance();
							cal.setTime(formattedDate);
							
							int month = cal.get(Calendar.MONTH);
							int day = cal.get(Calendar.DAY_OF_MONTH);
							int year = cal.get(Calendar.YEAR);
			
							
							formattedDate = inputDF.parse(format.format(member.getUserStartDt()));
							
							cal.setTime(formattedDate);
							int stMonth = cal.get(Calendar.MONTH);
							int stDay = cal.get(Calendar.DAY_OF_MONTH);
							int stYear = cal.get(Calendar.YEAR);
							
							formattedDate = inputDF.parse(format.format(member.getUserEndDt()));
							
							cal.setTime(formattedDate);
							int endMonth = cal.get(Calendar.MONTH);
							int endDay = cal.get(Calendar.DAY_OF_MONTH);
							int endYear = cal.get(Calendar.YEAR);
							
							// 1. year(���� �⵵)�� stYear���� Ŭ��� month�� day�� �ƹ�����̾���
							// 2. year�� stYear�� ������� month�� ����
							// 3. �̶� month�� stMonth���� Ŭ��� day�� �ƹ������ ����
							// 4. month�� stMonth�� ������� stDay�� ����
							// endYear�� 1, 2, 3, 4 �� ����
							if(year > stYear || (year == stYear && (month > stMonth || (month == stMonth && day >= stDay)))){
								if(year < endYear || (year == endYear && (month < endMonth || (month == endMonth && day <= endDay)))){
									return 1;
								}else {
									return 2;
								}
							}else {
								return 2;
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return -1;
						}
					}
				}
			}
			else {
				return -1;
			}
		}else {
			return 7;
		}
	}

	@Transactional
	public Member findByUserName(String username){
		return memberDao.findByUserName(username);
	}

	@Transactional
	public Member findByCustomInfo(String field, String value){
		return memberDao.findByCustomInfo(field, value);
	}

	@Transactional
	public int updateMemberInfo(Member member, int userSeq){
		return memberDao.updateMemberInfo(member, userSeq);
	}

	@Transactional
	public void updateMemberUserWithdrawal(String userStatus, Date withdrawalDt, int userSeq){
		memberDao.updateMemberUserWithdrawal(userStatus, withdrawalDt, userSeq);
	}

	@Transactional
	public void updateMemberEmailCheckInfo(String userStatus, Date emailChkDt,String emailChkGb, String emailChkSession, int userSeq) {
		memberDao.updateMemberEmailCheckInfo(userStatus,emailChkDt,emailChkGb,emailChkSession, userSeq);
	}

	@Transactional
	public void updateMemberLoginStatus(String loginStatus, int userSeq){
		memberDao.updateMemberLoginStatus(loginStatus, userSeq);
	}

	@Transactional
	public void updateMemberUserPeriod(Date loginDt, Date userStartDt, Date userEndDt, int userSeq){
		memberDao.updateMemberUserPeriod(loginDt, userStartDt, userEndDt, userSeq);
	}

	@Transactional
	public Member selectMemberSuccessYn(Member member) {
		return memberDao.selectMemberSuccessYn(member);
	}

	@Transactional
	public int selectMemberCount(Member member) {
		return memberDao.selectMemberCount(member);
	}

	@Transactional
	public Member selectMemberSuccessYn_(Member member) {
		return memberDao.selectMemberSuccessYn_(member);
	}

	@Transactional
	public int selectMemberCount_(Member member) {
		return memberDao.selectMemberCount_(member);
	}

	@Transactional
	public void updateMemberPw(String userId, String userPw) {
		memberDao.updateMemberPw(userId, userPw);
	}

	@Transactional
	public MemberList getListMember(int currentPage, int companySeq, int maxResult, String searchType, String searchValue, String isAvailable, boolean isMember ) {

		MemberList list = null;
		int pageSize = 10;
		int totalCount = 0;
		int startNo = 0;

		totalCount = memberDao.getListMemberCount(companySeq, searchType, searchValue, isAvailable, isMember);

		startNo = (currentPage - 1) * maxResult;

		List<Member> memberList = memberDao.getListMember(startNo, companySeq, maxResult, searchType, searchValue, isAvailable, isMember);

		list = new MemberList(pageSize, totalCount, currentPage, maxResult);
		list.setList(memberList);

		return list;
	}

	@Transactional
	public Member findByCustomInfo(String fieldName, int value) {
		return memberDao.findByCustomInfo(fieldName, value);
	}

	@Transactional
	public String findCompanyMemberIdByCompanySeqAndUserGb(int companySeq) {
		
		Member member = memberDao.findCompanyMemberIdByCompanySeqAndUserGb(companySeq);
		
		if (member != null)
			return member.getUserId();
		else return "";
	}
	@Transactional
	public int selectCountWithPermittedUserByCompanySeq(int companySeq){
		return memberDao.selectCountWithPermittedUserByCompanySeq(companySeq);
	}
	
	@Transactional
	public List<Member> getUserList(int companySeq, String[] useS, String searchValue, String searchType  ) {
		return memberDao.getUserList(companySeq, useS, searchValue, searchType );
	}

	@Transactional
	public List<Member> getPermitList(int companySeq, String[] useS) {
		return memberDao.getPermitList(companySeq, useS);
	}
	
	@Transactional
	public int deleteMemberInfo(int value) {
		return memberDao.deleteMemberInfo(value);
	}

	@Transactional
	public int getCompanySeqForInAppContentCopy(int userSeq) {
		return memberDao.getCompanySeqForInAppContentCopy("getCompanySeqForInAppContentCopy", userSeq);
	}

	@Transactional
	public String selectCompanyName(int company_seq) {
		return memberDao.selectCompanyName("selectCompanyName", company_seq);
	}
}