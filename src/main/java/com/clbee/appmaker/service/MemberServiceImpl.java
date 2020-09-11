package com.clbee.appmaker.service;

import com.clbee.appmaker.model.MemberList;
import com.clbee.appmaker.model.Member;
import com.clbee.appmaker.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepo memberRepo;

	@Override
	public void addMember( Member m ) {
		memberRepo.addMember( m );
	}
	
	@Override
	public int verifyIfExists( String DBName, String itSelf){
		return memberRepo.selectItselfForExisting(DBName, itSelf);
	}
	
	@Override
	public int logInVerify( String username, String password ){
		List<Member> appList = memberRepo.logInVerify(username, password);

		Iterator iter = appList.iterator();
		
		if(iter.hasNext()) {
			Member memberVO =  (Member)iter.next();
			if("1".equals(memberVO.getUserStatus())){
				// Ż��
				return 3;
			}else if("2".equals(memberVO.getUserStatus())) {
				// ����
				return 4;
			}else if("3".equals(memberVO.getUserStatus())) {
				// ���� Ż��
				return 5;
			}else if("5".equals(memberVO.getUserStatus())) {
				// ��� ���
				return 6;
			}else if("4".equals(memberVO.getUserStatus())) {
			
				if(!"1".equals(memberVO.getUserGb())) {
					return 1;
				}else {
					if( "2".equals(memberVO.getDateGb()) ) {
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
			
							
							formattedDate = inputDF.parse(format.format(memberVO.getUserStartDt()));
							
							cal.setTime(formattedDate);
							int stMonth = cal.get(Calendar.MONTH);
							int stDay = cal.get(Calendar.DAY_OF_MONTH);
							int stYear = cal.get(Calendar.YEAR);
							
							formattedDate = inputDF.parse(format.format(memberVO.getUserEndDt()));
							
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
							
							/*if((year >= stYear) && (month >= stMonth) && (day >= stDay)){
								if((year <= endYear) && (month <= endMonth) && (day <= endDay)){
									return 1;
								}else {
									return 2;
								}
							}else {
							}*/
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return -1;
						}
					}
				}
			}else {
				return -1;
			}
		}else {
			return 7;
		}
	}

	@Override
	public Member findByUserName(String username){
		return memberRepo.findByUserName(username);
	}

	@Override
	public Member findByCustomInfo(String DBName, String value){
		return memberRepo.findByCustomInfo(DBName, value);
	}

	@Override
	public int updateMemberInfo(Member m, int userNum){
		return memberRepo.updateMemberInfo(m, userNum);
	}

	@Override
	public Member selectMemberSuccessYn(Member memberVO) {
		return memberRepo.selectMemberSuccessYn(memberVO);
	}

	@Override
	public Integer selectMemberCount(Member memberVO) {
		return memberRepo.selectMemberCount(memberVO);
	}

	@Override
	public Member selectMemberSuccessYn_(Member memberVO) {
		return memberRepo.selectMemberSuccessYn_(memberVO);
	}

	@Override
	public Integer selectMemberCount_(Member memberVO) {
		return memberRepo.selectMemberCount_(memberVO);
	}

	@Override
	public void updateMemberPw(Member memberVO) {
		memberRepo.updateMemberPw(memberVO);		
	}

	@Override
	public MemberList getListMember( int currentPage, int companySeq, int maxResult, String searchType, String searchValue, String isAvailable, boolean isMember ) {

		MemberList list = null;
		int pageSize = 10;
		int totalCount = 0;
		int startNo = 0;
			try{
				totalCount = memberRepo.getListMemberCount(companySeq, searchType, searchValue, isAvailable, isMember);
				
				startNo = (currentPage - 1) * maxResult;
	
				List<Member> vo = memberRepo.getListMember(startNo, companySeq, maxResult, searchType, searchValue, isAvailable, isMember);

				list = new MemberList(pageSize, totalCount, currentPage, maxResult);
				list.setList(vo);
			}catch(Exception e){
				System.out.println("����");
				e.printStackTrace();
			}
		return list;
	}


	@Override
	public Member findByCustomInfo(String DBName, int value) {
		return memberRepo.findByCustomInfo(DBName, value);
	}


	@Override
	public String findCompanyMemberIdByCompanySeqAndUserGb(int companySeq){
		
		Member memberVO = memberRepo.findCompanyMemberIdByCompanySeqAndUserGb(companySeq);
		
		if (memberVO != null)
			return memberVO.getUserId();
		else return "";
	}
	@Override
	public int selectCountWithPermisionUserByCompanySeq( int companySeq){
		return memberRepo.selectCountWithPermisionUserByCompanySeq(companySeq); 
	}
	
	@Override
	public List<Member> getUserList( int companySeq, String[] useS, String searchValue, String searchType  ) {
		// TODO Auto-generated method stub
		return memberRepo.getUserList( companySeq, useS, searchValue, searchType );
	}


	@Override
	public List<Member> getPermitList(int companySeq, String[] useS) {
		// TODO Auto-generated method stub
		return memberRepo.getPermitList(companySeq, useS);
	}
	
	//20180213 - lsy : user delete
	@Override
	public int deleteMemberInfo(int value) {
		return memberRepo.deleteMemberInfo(value);
	}

	//20180619 - lsy : when app request(Library), load inapp info
	@Override
	public int getCompanySeqForInappContentCopy(int userSeq) {
		// TODO Auto-generated method stub
		return memberRepo.getCompanySeqForInappContentCopy("getCompanySeqForInappContentCopy", userSeq);
	}

	@Override
	public String selectCompanyName(int company_seq) {
		// TODO Auto-generated method stub
		return memberRepo.selectCompanyName("selectCompanyName", company_seq);
	}
}