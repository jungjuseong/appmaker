package com.clbee.appmaker.dao.impl;

import com.clbee.appmaker.dao.MemberDao;
import com.clbee.appmaker.model.Member;
import com.clbee.appmaker.util.ShaPassword;
import org.apache.ibatis.session.SqlSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.SessionFactory;

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
	public int updateMemberInfo(Member member, int userNum) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Member Member = session.get(Member.class, userNum);

			if(member.getChgDt() != null)
				Member.setChgDt(member.getChgDt());
			if(member.getChgIp() != null && !"".equals(member.getChgIp()))
				Member.setChgIp(member.getChgIp());
			if(member.getCompanyGb() != null && !"".equals(member.getCompanyGb()))
				Member.setCompanyGb(member.getCompanyGb());
			if(member.getCompanySeq() != 0 )
				Member.setCompanySeq(member.getCompanySeq());

			//������ ��ĭ���� ������ �� ����.
			if(member.getEmail() != null )
				Member.setEmail(member.getEmail());
			if(member.getEmailChkDt() != null )
				Member.setEmailChkDt(member.getEmailChkDt());
			if(member.getEmailChkGb() != null && !"".equals(member.getEmailChkGb()))
				Member.setEmailChkGb(member.getEmailChkGb());
			if(member.getEmailChkSession() != null && !"".equals(member.getEmailChkSession()))
				Member.setEmailChkSession(member.getEmailChkSession());
			if(member.getFirstName() != null && !"".equals(member.getFirstName()))
				Member.setFirstName(member.getFirstName());
			if(member.getLastName() != null && !"".equals(member.getLastName()))
				Member.setLastName(member.getLastName());
			if(member.getLoginDt() != null )
				Member.setLoginDt(member.getLoginDt());
			if(member.getPhone() != null && !"".equals(member.getPhone()))
				Member.setPhone(member.getPhone());
			if(member.getRegDt() != null  )
				Member.setRegDt(member.getRegDt());
			if(member.getRegIp() != null && !"".equals(member.getRegIp()))
				Member.setRegIp(member.getRegIp());
			if(member.getUserGb() != null && !"".equals(member.getUserGb()))
				Member.setUserGb(member.getUserGb());
			if(member.getUserId() != null && !"".equals(member.getUserId()))
				Member.setUserId(member.getUserId());										/*������ϰ���� �ؽ���*/
			if(member.getUserPw() != null && !"".equals(member.getUserPw()) && !"e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855".equals(member.getUserPw()))
				Member.setUserPw(member.getUserPw());
			if(member.getUserStatus() != null && !"".equals(member.getUserStatus()))
				Member.setUserStatus(member.getUserStatus());
			if(member.getWithdrawalDt() != null )
				Member.setWithdrawalDt(member.getWithdrawalDt());
			if(member.getOnedepartmentSeq() != null )
				Member.setOnedepartmentSeq(member.getOnedepartmentSeq());
			if(member.getTwodepartmentName() != null )
				Member.setTwodepartmentSeq(member.getTwodepartmentSeq());
			if(member.getDateGb() != null && !"".equals(member.getDateGb()))
				Member.setDateGb(member.getDateGb());
			if(member.getUserStartDt() != null )
				Member.setUserStartDt(member.getUserStartDt());
			if(member.getUserEndDt() != null)
				Member.setUserEndDt(member.getUserEndDt());
			if(member.getSessionId() != null && !"".equals(member.getSessionId()))
				Member.setSessionId(member.getSessionId());
			if(member.getLoginStatus() != null && !"".equals(member.getLoginStatus()))
				Member.setLoginStatus(member.getLoginStatus());
			if(member.getLoginDeviceuuid() != null && !"".equals(member.getLoginDeviceuuid()))
				Member.setLoginDeviceuuid(member.getLoginDeviceuuid());

			//20180511 : lsy - group add
			if(member.getGroupName() != null && !"".equals(member.getGroupName()))
				Member.setGroupName(member.getGroupName());

			session.update(Member);
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
	public int selectItselfForExisting(String DBname, String itSelf ){

		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Member.class);
			cr.add(Restrictions.eq(DBname, itSelf)); // where

			Member Member = (Member)cr.uniqueResult();
			tx.commit();

			if(Member == null){
				return 0;
			}else{
				return 1;
			}
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
			return 2;
		}finally {
			session.close();
		}
	}

	@Override
	public List<Member> logInVerify(String ID, String pswd ){

		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = null;
		List<Member> list = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Member.class);

			Criterion username = Restrictions.eq("userId", ID);
			Criterion password = Restrictions.eq("userPw", pswd);

			LogicalExpression andGate = Restrictions.and(username, password);
			cr.add(andGate );
			list = cr.list();

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
	public Member findByCustomInfo(String DBName, String value) {

		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = null;
		Member Member = null;

		try {
			tx = session.beginTransaction();

			Criteria cr = session.createCriteria(Member.class);
			Criterion user = Restrictions.eq(DBName, value);

			cr.add(user);
			Member = (Member) cr.uniqueResult();

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

	public String changeSHA256(String str){
		String SHA = "";
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();

		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
			SHA = null;
		}
		return SHA;
	}

	@Override
	public Member selectMemberSuccessYn(Member Member) {

		Session session = this.sessionFactory.getCurrentSession();

		Member result = null;
		try {
			Criteria criteria= session.createCriteria(Member.class);

			criteria.add(Restrictions.eq("firstName", Member.getFirstName()));
			criteria.add(Restrictions.eq("lastName", Member.getLastName()));
			criteria.add(Restrictions.eq("email", Member.getEmail()));

			result = (Member) criteria.uniqueResult();

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}

		return result;
	}

	@Override
	public Integer selectMemberCount(Member Member) {

		Session session = this.sessionFactory.getCurrentSession();

		Number Count = null;
		try {

			Criteria criteria= session.createCriteria(Member.class);
			Count = ((Number) session.createCriteria(Member.class)
					.add(Restrictions.eq("firstName", Member.getFirstName()))
					.add(Restrictions.eq("lastName", Member.getLastName()))
					.add(Restrictions.eq("email", Member.getEmail()))
					.setProjection(Projections.rowCount()).uniqueResult());

		}catch (Exception e) {

			e.printStackTrace();
		}finally {
			session.close();
		}
		return Count.intValue();
	}

	@Override
	public Member selectMemberSuccessYn_(Member Member) {

		Session session = this.sessionFactory.getCurrentSession();
		Member result = null;

		try {
			Criteria criteria= session.createCriteria(Member.class);

			criteria.add(Restrictions.eq("userId", Member.getUserId()));
			criteria.add(Restrictions.eq("email", Member.getEmail()));

			result = (Member) criteria.uniqueResult();

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}

		return result;
	}

	@Override
	public Integer selectMemberCount_(Member Member) {
		Session session = this.sessionFactory.getCurrentSession();
		Number Count = null;

		try {

			Criteria criteria= session.createCriteria(Member.class);
			Count = ((Number) session.createCriteria(Member.class)
					.add(Restrictions.eq("userId", Member.getUserId()))
					.add(Restrictions.eq("email", Member.getEmail()))
					.setProjection(Projections.rowCount()).uniqueResult());

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return Count.intValue();
	}

	@Override
	public void updateMemberPw(Member Member) {

		Session session = this.sessionFactory.getCurrentSession();

		try {
			Member.setUserPw(ShaPassword.changeSHA256(Member.getUserPw()));

			String hql = "UPDATE Member set "
					+ "user_Pw = :user_Pw"
					+" WHERE user_Id = :user_Id";


			Query query1 = session.createQuery(hql)
					.setParameter("user_Pw", Member.getUserPw())
					.setParameter("user_Id", Member.getUserId());

			query1.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Override
	public List<Member> getListMember(int startNo, int companySeq, int MaxResult, String searchType, String searchValue, String isAvailable, boolean isMember) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = null;
		List<Member> list = null;

		try {
			tx = session.beginTransaction();

			Query query = null;
			if(isMember == true) {//service가 아닌경우
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
					}else {
						query = session.createQuery("FROM Member E "
								+ "WHERE (E.companySeq = :companySeq AND E.userId like :userId )"
								+ " AND (E.userGb = '63')"
								+ " ORDER BY E.regDt DESC ")
								.setFirstResult(startNo)
								.setMaxResults(10)
								.setParameter("companySeq", companySeq)
								.setParameter("userId", "%"+searchValue+"%");
					}
				}else if("userName".equals(searchType)) {
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

			list = (List<Member>)query.list();

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
	public Member findByCustomInfo(String DBName, int value) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = null;
		Member Member = null;

		try {
			tx = session.beginTransaction();

			Criteria cr = session.createCriteria(Member.class);
			Criterion user = Restrictions.eq(DBName, value);

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
	public int getCompanySeqForInappContentCopy(String DBName, int userSeq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, userSeq);
	}

	@Override
	public String selectCompanyName(String DBName, int company_seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, company_seq);
	}
}
