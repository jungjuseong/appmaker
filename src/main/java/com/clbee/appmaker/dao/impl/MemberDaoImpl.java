package com.clbee.appmaker.dao.impl;

import com.clbee.appmaker.dao.MemberDao;
import com.clbee.appmaker.model.Member;
import com.clbee.appmaker.util.ShaPassword;
import org.apache.ibatis.session.SqlSession;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.transaction.Transactional;

@Repository
@Transactional
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Member addMember(Member member) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(member);
		return member;
	}

	@Override
	public int updateMemberInfo(Member updated, int userNum) {

		Session session = this.sessionFactory.getCurrentSession();

		try {
			Member member = session.get(Member.class, userNum);

			if(updated.getChgDt() != null)
				member.setChgDt(updated.getChgDt());
			if(updated.getChgIp() != null && !"".equals(updated.getChgIp()))
				member.setChgIp(updated.getChgIp());
			if(updated.getCompanyGb() != null && !"".equals(updated.getCompanyGb()))
				member.setCompanyGb(updated.getCompanyGb());
			if(updated.getCompanySeq() != 0 )
				member.setCompanySeq(updated.getCompanySeq());

			if(updated.getEmail() != null )
				member.setEmail(updated.getEmail());
			if(updated.getEmailChkDt() != null )
				member.setEmailChkDt(updated.getEmailChkDt());
			if(updated.getEmailChkGb() != null && !"".equals(updated.getEmailChkGb()))
				member.setEmailChkGb(updated.getEmailChkGb());
			if(updated.getEmailChkSession() != null && !"".equals(updated.getEmailChkSession()))
				member.setEmailChkSession(updated.getEmailChkSession());
			if(updated.getFirstName() != null && !"".equals(updated.getFirstName()))
				member.setFirstName(updated.getFirstName());
			if(updated.getLastName() != null && !"".equals(updated.getLastName()))
				member.setLastName(updated.getLastName());
			if(updated.getLoginDt() != null )
				member.setLoginDt(updated.getLoginDt());
			if(updated.getPhone() != null && !"".equals(member.getPhone()))
				member.setPhone(updated.getPhone());
			if(member.getRegDt() != null  )
				member.setRegDt(updated.getRegDt());
			if(updated.getRegIp() != null && !"".equals(member.getRegIp()))
				member.setRegIp(updated.getRegIp());
			if(updated.getUserGb() != null && !"".equals(member.getUserGb()))
				member.setUserGb(updated.getUserGb());
			if(updated.getUserId() != null && !"".equals(member.getUserId()))
				member.setUserId(updated.getUserId());										/*������ϰ���� �ؽ���*/
			if(member.getUserPw() != null && !"".equals(member.getUserPw()) && !"e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855".equals(member.getUserPw()))
				member.setUserPw(updated.getUserPw());
			if(updated.getUserStatus() != null && !"".equals(member.getUserStatus()))
				member.setUserStatus(updated.getUserStatus());
			if(updated.getWithdrawalDt() != null )
				member.setWithdrawalDt(updated.getWithdrawalDt());
			if(updated.getOnedepartmentSeq() != null )
				member.setOnedepartmentSeq(updated.getOnedepartmentSeq());
			if(updated.getTwodepartmentName() != null )
				member.setTwodepartmentSeq(updated.getTwodepartmentSeq());
			if(updated.getDateGb() != null && !"".equals(member.getDateGb()))
				member.setDateGb(updated.getDateGb());
			if(updated.getUserStartDt() != null )
				member.setUserStartDt(updated.getUserStartDt());
			if(updated.getUserEndDt() != null)
				member.setUserEndDt(updated.getUserEndDt());
			if(updated.getSessionId() != null && !"".equals(member.getSessionId()))
				member.setSessionId(updated.getSessionId());
			if(updated.getLoginStatus() != null && !"".equals(member.getLoginStatus()))
				member.setLoginStatus(updated.getLoginStatus());
			if(updated.getLoginDeviceuuid() != null && !"".equals(member.getLoginDeviceuuid()))
				member.setLoginDeviceuuid(updated.getLoginDeviceuuid());

			//20180511 : lsy - group add
			if(member.getGroupName() != null && !"".equals(member.getGroupName()))
				member.setGroupName(member.getGroupName());

			session.update(member);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	@Override
	public int verfyIfExists(String fieldName, String itSelf){

		Session session = this.sessionFactory.getCurrentSession();

		var queryString = String.format("FROM Member M WHERE M.%s=:itSelf", fieldName);
		var memberList = session.createQuery(queryString).
				setParameter("itSelf", itSelf)
				.list();

		return (memberList.size() != 0) ? 1 : 0;
	}

	@Override
	public List<Member> verifyLogin(String username, String userpw ) {

		Session session = this.sessionFactory.getCurrentSession();
		List<Member> memberList = session.createQuery("FROM Member M WHERE M.userId = :userId and M.userPw = :userPw")
						.setParameter("userId", username)
						.setParameter("userPw", userpw).list();
		return memberList;
	}

	@Override
	public Member findByCustomInfo(String DBName, String value) {
		Session session = this.sessionFactory.getCurrentSession();
		Member Member = null;

		try {
			Criteria cr = session.createCriteria(Member.class);
			Criterion user = Restrictions.eq(DBName, value);

			cr.add(user);
			Member = (Member) cr.uniqueResult();

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}

		return Member;
	}

	@Override
	public Member findByUserName(String username){

		Session session = this.sessionFactory.getCurrentSession();
		List<Member> memberList = session.createQuery("FROM Member M WHERE M.userId = :userId")
				.setParameter("userId", username).list();

		if (memberList.size() == 1)
			return memberList.get(0);

		return null;
	}

	@Override
	public Member findCompanyMemberIdByCompanySeqAndUserGb(int companySeq ){

		Session session = this.sessionFactory.getCurrentSession();
		Member Member = null;

		List<Member> memberList = session.createQuery("FROM Member M WHERE M.companySeq = :companySeq and M.userGb = \"127\"")
				.setParameter("companySeq", companySeq).list();

		if (memberList.size() == 1)
			return memberList.get(0);

		return null;
	}

	@Override
	public Member selectMemberSuccessYn(Member member) {

		Session session = this.sessionFactory.getCurrentSession();

		List<Member> memberList = session.createQuery("FROM Member M WHERE M.firstName = :firstName and M.lastName = :lastName and M.email = :email and")
				.setParameter("firstName", member.getFirstName())
				.setParameter("lastName", member.getLastName())
				.setParameter("email", member.getEmail())
				.list();

		if (memberList.size() == 1)
			return memberList.get(0);

		return null;
	}

	@Override
	public int selectMemberCount(Member member) {

		Session session = this.sessionFactory.getCurrentSession();

		Query query = session.createQuery(
				"select count(*) from Member m where m.firstName=:firstName and m.lastName=:lastName and m.email=:email")
				.setParameter("firstName", member.getFirstName())
				.setParameter("lastName", member.getLastName())
				.setParameter("email",member.getEmail());

		return (int) query.uniqueResult();
	}

	@Override
	public Member selectMemberSuccessYn_(Member member) {

		Session session = this.sessionFactory.getCurrentSession();
		List<Member> memberList = session.createQuery("FROM Member M WHERE M.userId = :userId and M.email = :email and")
				.setParameter("userId", member.getUserId())
				.setParameter("email", member.getEmail())
				.list();

		if (memberList.size() == 1)
			return memberList.get(0);

		return null;
	}

	@Override
	public int selectMemberCount_(Member member) {
		Session session = this.sessionFactory.getCurrentSession();

		Query query = session.createQuery(
				"select count(*) from Member m where m.userId=:userId and m.email=:email")
				.setParameter("userId", member.getUserId())
				.setParameter("email",member.getEmail());

		return (int) query.uniqueResult();
	}

	@Override
	public void updateMemberPw(Member member) {

		Session session = this.sessionFactory.getCurrentSession();

		member.setUserPw(ShaPassword.changeSHA256(member.getUserPw()));

		Query query1 = session.createQuery("UPDATE Member set user_pw=:userPw WHERE user_Id=:userId")
				.setParameter("userPw", member.getUserPw())
				.setParameter("userId", member.getUserId());

		query1.executeUpdate();
	}

	@Override
	public List<Member> getListMember(int startNo, int companySeq, int maxResult, String searchType, String searchValue, String isAvailable, boolean isMember) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Member> list = null;

		Query query = null;
		if (isMember) {
			if("userId".equals(searchType)) {
				if(isAvailable != null && "false".equals(isAvailable)){
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.companySeq = :companySeq AND E.userId like :userId) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
							+ " AND (E.userGb = '63')"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("companySeq", companySeq)
							.setParameter("userId", "%"+searchValue+"%");
				}
				else {
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.companySeq = :companySeq AND E.userId like :userId )"
							+ " AND (E.userGb = '63')"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("companySeq", companySeq)
							.setParameter("userId", "%"+searchValue+"%");
				}
			}
			else if("userName".equals(searchType)) {
				if(isAvailable != null && "false".equals(isAvailable)){
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.companySeq = :companySeq AND concat(E.lastName, E.firstName) like :fullName ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
							+ " AND (E.userGb = '63')"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("companySeq", companySeq)
							.setParameter("fullName", "%"+searchValue+"%");
				}else {
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.companySeq = :companySeq AND concat(E.lastName, E.firstName) like :fullName )"
							+ " AND (E.userGb = '63')"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("companySeq", companySeq)
							.setParameter("fullName", "%"+searchValue+"%");
				}
			}else if("onedepartmentName".equals(searchType)){
				if(isAvailable != null && "false".equals(isAvailable)){
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.companySeq = :companySeq AND E.onedepartmentName like :onedepartmentName ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
							+ " AND (E.userGb = '63')"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("companySeq", companySeq)
							.setParameter("onedepartmentName", "%"+searchValue+"%");
				}else {
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.companySeq = :companySeq AND E.onedepartmentName like :onedepartmentName )"
							+ " AND (E.userGb = '63')"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("companySeq", companySeq)
							.setParameter("onedepartmentName", "%"+searchValue+"%");
				}
			}else if("twodepartmentName".equals(searchType)){
				if(isAvailable != null && "false".equals(isAvailable)){
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.companySeq = :companySeq AND E.twodepartmentName like :twodepartmentName ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
							+ " AND (E.userGb = '63')"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("companySeq", companySeq)
							.setParameter("twodepartmentName", "%"+searchValue+"%");
				}else {
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.companySeq = :companySeq AND E.twodepartmentName like :twodepartmentName )"
							+ " AND (E.userGb = '63')"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("companySeq", companySeq)
							.setParameter("twodepartmentName", "%"+searchValue+"%");
				}
			}else if("userGroup".equals(searchType)) {//20180525 : lsy - searchtype group add
				if(isAvailable != null && "false".equals(isAvailable)){
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.companySeq = :companySeq AND E.userGroup like :userGroup ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
							+ " AND (E.userGb = '63')"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("companySeq", companySeq)
							.setParameter("userGroup", "%"+searchValue+"%");
				}else {
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.companySeq = :companySeq AND E.userGroup like :userGroup )"
							+ " AND (E.userGb = '63')"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("companySeq", companySeq)
							.setParameter("userGroup", "%"+searchValue+"%");
				}
			}else{
				System.out.println("searchType Is Anonymous");
				if(isAvailable != null && "false".equals(isAvailable)){
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.companySeq = :companySeq ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
							+ " AND (E.userGb = '63')"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("companySeq", companySeq);
				}else {
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.companySeq = :companySeq )"
							+ " AND (E.userGb = '63')"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("companySeq", companySeq);
				}
			}
		}else{//service인 경우
			if("userId".equals(searchType)) {
				System.out.println("searchType Is userId");
				if(isAvailable != null && "false".equals(isAvailable)){
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.userGb = :userGb AND E.userId like :userId ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("userGb", "127")
							.setParameter("userId", "%"+searchValue+"%");
				}else {
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.userGb = :userGb AND E.userId like :userId )"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("userGb", "127")
							.setParameter("userId", "%"+searchValue+"%");
				}
			}else if("userName".equals(searchType)) {
				System.out.println("searchType Is userName");
				if(isAvailable != null && "false".equals(isAvailable)){
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.userGb = :userGb AND concat(E.lastName, E.firstName) like :fullName ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("userGb", "127")
							.setParameter("fullName", "%"+searchValue+"%");
				}else {
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.userGb = :userGb AND concat(E.lastName, E.firstName) like :fullName )"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("userGb", "127")
							.setParameter("fullName", "%"+searchValue+"%");
				}
			}else if("userGroup".equals(searchType)) {//20180525 : lsy - searchtype group add
				System.out.println("searchType Is userGroup");
				if(isAvailable != null && "false".equals(isAvailable)){
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.userGb = :userGb AND E.userGroup like :userGroup ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("userGb", "127")
							.setParameter("userGroup", "%"+searchValue+"%");
				}else {
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.userGb = :userGb AND E.userGroup like :userGroup )"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("userGb", "127")
							.setParameter("userGroup", "%"+searchValue+"%");
				}
			}else {
				System.out.println("searchType Is Anonymous");
				if(isAvailable != null && "false".equals(isAvailable)){
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.userGb = :userGb ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("userGb", "127");
				}else {
					query = session.createQuery("FROM Member E "
							+ "WHERE (E.userGb = :userGb  )"
							+ " ORDER BY E.regDt DESC ")
							.setFirstResult(startNo)
							.setMaxResults(10)
							.setParameter("userGb", "127");
				}
			}
		}

		if(isAvailable != null && "false".equals(isAvailable)){
			query.setParameter("userStatus1", "4").setParameter("userStatus2", "5");
		}

		return (List<Member>)query.list();
	}

	@Override
	public int getListMemberCount(int companySeq, String searchType, String searchValue, String isAvailable, boolean isMember) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = null;
		int totalNumber = 0;

		try {
			tx = session.beginTransaction();

			Query query = null;
			if(isMember == true) {
				if("userId".equals(searchType)) {
					if(isAvailable != null && "false".equals(isAvailable)){
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.companySeq = :companySeq AND E.userId like :userId ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
								+ " AND (E.userGb = '63')"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("companySeq", companySeq)
								.setParameter("userId", "%"+searchValue+"%");
					}else {
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.companySeq = :companySeq AND E.userId like :userId )"
								+ " AND (E.userGb = '63') "
								+ " ORDER BY E.regDt DESC ")
								.setParameter("companySeq", companySeq)
								.setParameter("userId", "%"+searchValue+"%");
					}
				}else if("userName".equals(searchType)) {
					System.out.println("searchType Is userName");
					if(isAvailable != null && "false".equals(isAvailable)){
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.companySeq = :companySeq AND concat(E.lastName, E.firstName) like :fullName ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
								+ " AND (E.userGb = '63')"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("companySeq", companySeq)
								.setParameter("fullName", "%"+searchValue+"%");
					}else {
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.companySeq = :companySeq AND concat(E.lastName, E.firstName) like :fullName )"
								+ " AND (E.userGb = '63')"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("companySeq", companySeq)
								.setParameter("fullName", "%"+searchValue+"%");
					}
				}else if("onedepartmentName".equals(searchType)){
					if(isAvailable != null && "false".equals(isAvailable)){
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.companySeq = :companySeq AND E.onedepartmentName like :onedepartmentName ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
								+ " AND (E.userGb = '63')"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("companySeq", companySeq)
								.setParameter("onedepartmentName", "%"+searchValue+"%");
					}else {
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.companySeq = :companySeq AND E.onedepartmentName like :onedepartmentName )"
								+ " AND (E.userGb = '63')"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("companySeq", companySeq)
								.setParameter("onedepartmentName", "%"+searchValue+"%");
					}
				}else if("twodepartmentName".equals(searchType)){
					if(isAvailable != null && "false".equals(isAvailable)){
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.companySeq = :companySeq AND E.twodepartmentName like :twodepartmentName ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
								+ " AND (E.userGb = '63')"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("companySeq", companySeq)
								.setParameter("twodepartmentName", "%"+searchValue+"%");
					}else {
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.companySeq = :companySeq AND E.twodepartmentName like :twodepartmentName )"
								+ " AND (E.userGb = '63')"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("companySeq", companySeq)
								.setParameter("twodepartmentName", "%"+searchValue+"%");
					}
				}else if("userGroup".equals(searchType)) {//20180525 : lsy - searchtype group add
					if(isAvailable != null && "false".equals(isAvailable)){
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.companySeq = :companySeq AND E.userGroup like :userGroup ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
								+ " AND (E.userGb = '63')"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("companySeq", companySeq)
								.setParameter("userGroup", "%"+searchValue+"%");
					}else {
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.companySeq = :companySeq AND E.userGroup like :userGroup )"
								+ " AND (E.userGb = '63')"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("companySeq", companySeq)
								.setParameter("userGroup", "%"+searchValue+"%");
					}
				}else {
					System.out.println("searchType Is Anonymous");
					if(isAvailable != null && "false".equals(isAvailable)){
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.companySeq = :companySeq ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
								+ " AND (E.userGb = '63')"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("companySeq", companySeq);
					}else {
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.companySeq = :companySeq )"
								+ " AND (E.userGb = '63')"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("companySeq", companySeq);
					}
				}
			}else {
				if("userId".equals(searchType)) {
					System.out.println("searchType Is userId");
					if(isAvailable != null && "false".equals(isAvailable)){
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.userGb = :userGb AND E.userId like :userId ) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("userGb", "127")
								.setParameter("userId", "%"+searchValue+"%");
					}else {
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.userGb = :userGb AND E.userId like :userId )"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("userGb", "127")
								.setParameter("userId", "%"+searchValue+"%");
					}
				}else if("userName".equals(searchType)) {
					System.out.println("searchType Is userName");
					if(isAvailable != null && "false".equals(isAvailable)){
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.userGb = :userGb AND concat(E.lastName, E.firstName) like :fullName) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("userGb", "127")
								.setParameter("fullName", "%"+searchValue+"%");
					}else {
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.userGb = :userGb AND E.userName like :userName )"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("userGb", "127")
								.setParameter("userName", "%"+searchValue+"%");
					}
				}else if("userGroup".equals(searchType)) {//20180525 : lsy - searchtype group add
					System.out.println("searchType Is userGroup");
					if(isAvailable != null && "false".equals(isAvailable)){
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.userGb = :userGb AND E.userGroup like :userGroup) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("userGb", "127")
								.setParameter("userGroup", "%"+searchValue+"%");
					}else {
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.userGb = :userGb AND E.userGroup like :userGroup)"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("userGb", "127")
								.setParameter("userGroup", "%"+searchValue+"%");
					}
				}else {
					System.out.println("searchType Is Anonymous");
					if(isAvailable != null && "false".equals(isAvailable)){
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.userGb = :userGb) AND (E.userStatus = :userStatus1 OR E.userStatus = :userStatus2)"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("userGb", "127");
					}else {
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.userGb = :userGb)"
								+ " ORDER BY E.regDt DESC ")
								.setParameter("userGb", "127");
					}
				}
			}

			if(isAvailable != null && "false".equals(isAvailable)){
				query.setParameter("userStatus1", "4").setParameter("userStatus2", "5");
			}

			totalNumber = query.list().size();
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return totalNumber;
	}

	@Override
	public Member findByCustomInfo(String fieldName, int value) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = null;
		Member Member = null;

		try {
			tx = session.beginTransaction();

			Criteria cr = session.createCriteria(Member.class);
			Criterion user = Restrictions.eq(fieldName, value);

			cr.add(user);
			Member = (Member) cr.uniqueResult();
				
			/*	�� ������ username�� �ش��ϴ� �κ��� id�ϰ�쿡 ����
				Member Member = (Member) session.load(Member.class, username); 
			*/
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}

		return Member;
	}

	@Override
	public int selectCountWithPermisionUserByCompanySeq(int companySeq){
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = null;
		Number Count = null;

		try{
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Member.class);

			Count = ((Number) session.createCriteria(Member.class)
					.add(Restrictions.eq("companySeq", companySeq))
					.add(
							Restrictions.and(
									Restrictions.or(
											Restrictions.eq("userGb", "1"),
											Restrictions.eq("userGb", "5"),
											Restrictions.eq("userGb", "21"),
											Restrictions.eq("userGb", "29"),
											Restrictions.eq("userGb", "127")
									),
									Restrictions.eq("userStatus", "4")
							)
					)
					.setProjection(Projections.rowCount()).uniqueResult());


			tx.commit();
		}catch(Exception e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}

		return  Count.intValue();
	}

	@Override
	public int selectCountByCompanySeq(int companySeq) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = null;
		Number Count = null;

		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Member.class);


			Count = ((Number) session.createCriteria(Member.class)
					.add(Restrictions.eq("companySeq", companySeq))
					.add(Restrictions.eq("userGb", "1"))
					.add(Restrictions.eq("userGb", "5"))
					.add(Restrictions.eq("userGb", "21"))
					.add(Restrictions.eq("userGb", "29"))
					.setProjection(Projections.rowCount()).uniqueResult());

			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return Count.intValue();
	}

	@Override
	public List<Member> getUserList(int companySeq, String[] useS, String searchValue, String searchType) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = null;
		List list = null;
		try {
			tx = session.beginTransaction();

			String clause ="";
			String searchClause = "";

			if(useS != null && useS.length > 0){
				clause = " AND T.userSeq Not In (";
				for(int i =0; i< useS.length ; i ++) {
					if(i == useS.length-1) clause += useS[i]+" ";
					else if(useS.length == 1) clause += useS[i]+" ";
					else clause += useS[i]+", ";
				}
				clause += ")";
			}


			if(searchValue != null && searchType != null) {
				switch(Integer.parseInt(searchType)) {
					case 1:
						searchClause =" AND T.userId like '%" + searchValue+"%'";
						break;
					case 2:
						searchClause =" AND concat(T.lastName, T.firstName) like '%" + searchValue+"%'";
						break;
					case 3:
						searchClause =" AND T.onedepartmentName like '%" + searchValue+"%'";
						break;
					case 4:
						searchClause =" AND T.twodepartmentName like '%" + searchValue+"%'";
						break;
				}
			}

			System.out.println("mysql Sentence = " + clause);
			System.out.println("mysql CompanySeq = " + companySeq);

			Query query = session.createQuery("FROM Member T "
					+ " WHERE (T.userGb = '1' OR T.userGb = '5' OR T.userGb = '21' OR T.userGb = '29' OR T.userGb = '127') "
					+ " AND T.userStatus = :userStatus "
					+ " AND T.companySeq = :companySeq "
					+ clause
					+ searchClause
					+ " ORDER BY T.regDt DESC ")
					//.setFirstResult(startNo).setMaxResults(10)
					.setParameter("userStatus", "4")
					.setParameter("companySeq", companySeq);
			//.setParameter("userSeq", useS);


			list = query.list();
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Member> getPermitList(int companySeq, String[] useS) {

		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = null;
		List list = null;

		try {
			tx = session.beginTransaction();

			String clause ="";

			if(useS != null && useS.length > 0){
				clause = " AND T.userSeq In (";
				for(int i =0; i< useS.length ; i ++) {
					if(i == useS.length-1) clause += useS[i]+" ";
					else if(useS.length == 1) clause += useS[i]+" ";
					else clause += useS[i]+", ";
				}
				clause += ")";
			}else {
				return null;
			}


			System.out.println("mysql Sentence = " + clause);
			System.out.println("mysql CompanySeq = " + companySeq);

			Query query = session.createQuery("FROM Member T "
					+ " WHERE (T.userGb = '1' OR T.userGb = '5' OR T.userGb = '21' OR T.userGb = '29' OR T.userGb = '127')"
					+ " AND T.companySeq = :companySeq "
					+ clause
					+ " ORDER BY T.regDt DESC ")
					//.setFirstResult(startNo).setMaxResults(10)
					.setParameter("companySeq", companySeq);
			//.setParameter("userSeq", useS);


			list = query.list();
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}

	@Override
	public int deleteMemberInfo(int userNum ) {

		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			Member Member = (Member)session.get(Member.class, userNum);

			session.delete(Member);
			tx.commit();
			return 1;
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
			return 0;
		}finally {
			session.close();
		}
	}

	@Override
	public int getCompanySeqForInAppContentCopy(String DBName, int userSeq) {
		return sqlSession.selectOne(DBName, userSeq);
	}

	@Override
	public String selectCompanyName(String DBName, int company_seq) {
		return sqlSession.selectOne(DBName, company_seq);
	}
}
