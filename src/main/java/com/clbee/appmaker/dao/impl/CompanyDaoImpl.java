package com.clbee.appmaker.dao.impl;

import java.math.BigInteger;
import java.util.List;

import com.clbee.appmaker.dao.CompanyDao;
import org.apache.ibatis.session.SqlSession;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clbee.appmaker.util.ShaPassword;
import com.clbee.appmaker.util.GetRenewPassword;
import com.clbee.appmaker.model.Company;
import com.clbee.appmaker.model.Member;

@Repository
public class CompanyDaoImpl implements CompanyDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public int insertCompanyInfoWithProcedure(Company company) {
		Session session = getSession();
		Transaction t = null;
		int companySeq = 0;
		
		try {
			t = session.beginTransaction();
	
			/**************************************************/
			/* Call MYSQL Stored Procedure and MAP it to bean */
			/***********************************************/
			Query callStoredProcedure_MYSQL = session.createSQLQuery("CALL SP_INSERT_COMPANY (:companyName, :companyTel, :zipCode, :addrFirst, :addrDetail)");
		
			callStoredProcedure_MYSQL.setString("companyName", company.getCompanyName());
			callStoredProcedure_MYSQL.setString("companyTel", company.getCompanyTel());
			callStoredProcedure_MYSQL.setString("zipCode", company.getZipcode());
			callStoredProcedure_MYSQL.setString("addrFirst", company.getAddrFirst());
			callStoredProcedure_MYSQL.setString("addrDetail", company.getAddrDetail());
	
			/* callStoredProcedure_MSSQL.list() will execute stored procedure and return the value */
			List<BigInteger> companyList = callStoredProcedure_MYSQL.list();
			System.out.println("@@@@@@@@@@@@"+companyList.get(0).intValue());
			
			companySeq = companyList.get(0).intValue();
	
			t.commit();
		}catch (Exception e) {
			if(t != null) t.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		
		return companySeq;
	}

	@Override
	public Company findByCustomInfo(String DBName, String value) {
		Session session = getSession();
		Transaction tx = null;
		Company company = null;
		
		try {
			tx = session.beginTransaction();
	
			Criteria cr = session.createCriteria(Company.class);
			Criterion user = Restrictions.eq(DBName, value);
	
			cr.add(user);
			company = (Company) cr.uniqueResult();
	
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		return company;
	}
	
	@Override
	public Company findByCustomInfo(String DBName, int value) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = null;
		Company company = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(Company.class);
			Criterion user = Restrictions.eq(DBName, value);
			
			cr.add(user);
			company = (Company) cr.uniqueResult();
	
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		return company;
	}

	@Override
	public String id_overlap_chk(String id) {
		Session session = getSession();
		Transaction tx = null;
		
		 String result = "";
		try {
			tx = session.beginTransaction();
			
			 Criteria criteria= session.createCriteria(Member.class);
			 criteria.add(Restrictions.like("user_id", id));
			 List list=criteria.list();
			 if(list.size()>0){
				 result="|notUsed";
			 }else{
				 result=id+"|okay";
			 }
			
			 tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		
		return result;
		
	}
	
	@Override
	public String send_pw_mail(String myId, String myMail) {
		
		Session session = getSession();
		Transaction tx = null;
		String result="";
		
		try {
			tx = session.beginTransaction();
			
			Criteria criteria= session.createCriteria(Member.class);
			Criterion eqMyId = Restrictions.eq("user_id", myId);
			Criterion eqMyMail = Restrictions.eq("user_email", myMail);
			LogicalExpression andGate = Restrictions.and(eqMyId, eqMyMail);
			criteria.add(andGate );
			
			
			if(criteria.uniqueResult() == null){
				result="noMatch";
			}
			else {
				Member member = (Member)criteria.uniqueResult();
				String newPw=GetRenewPassword.getRenewPassword();
				member.setUserPw(ShaPassword.changeSHA256(newPw));
				session.saveOrUpdate(member);
				
				result=newPw;
			}
		
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		
		return result;
	}

	
	@Override
	public Company getComInfo(String companyID) {
		Session session = getSession();
		Transaction tx = null;
		Company company = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria criteria= session.createCriteria(Company.class);
			criteria.add(Restrictions.eq("company_id", companyID));
			
			company = (Company)criteria.uniqueResult();
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		
		return company;
	}
	
	@Override
	public int updateCompanyInfo(Company updated, int companySeq) {
		Session session = getSession();
		Transaction tx = null;		
		
		try {
			tx = session.beginTransaction();
			Company company = (Company)session.get(Company.class, companySeq);
			
			if(updated.getAddrDetail() != null && !"".equals(updated.getAddrDetail()))
				company.setAddrDetail(updated.getAddrDetail());
			if(updated.getAddrFirst() != null && !"".equals(updated.getAddrFirst()))
				company.setAddrFirst(updated.getAddrFirst());
			if(updated.getCompanyName() != null && !"".equals(updated.getCompanyName())) 
				company.setCompanyName(updated.getCompanyName());
			if(updated.getCompanyStatus() != null && !"".equals(updated.getCompanyStatus()))
				company.setCompanyStatus(updated.getCompanyStatus());	
			if(updated.getCompanyTel() != null && !"".equals(updated.getCompanyTel()))
				company.setCompanyTel(updated.getCompanyTel());
			if(updated.getRegDt() != null )
				company.setRegDt(updated.getRegDt());	
			if(updated.getWithdrawalDt() != null )
				company.setWithdrawalDt(updated.getWithdrawalDt());	
			if(updated.getZipcode() != null && !"".equals(updated.getZipcode()))
				company.setZipcode(updated.getZipcode());			

			session.update(company);
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
	public String changePwChk(Member m, String userID, String userPW) {
		
		Session session = getSession();
		String result = "";
		
		try {
			String chPw = ShaPassword.changeSHA256(userPW);

			System.out.println("COMPANY DAO [changePwChk] = " + m.getUserId());
			System.out.println("COMPANY DAO [changePwChk] = " + m.getUserPw());
			Criteria criteria= session.createCriteria(Member.class);
			Criterion user_id = Restrictions.eq("user_id", userID);
			Criterion user_pw = Restrictions.eq("user_pw_1", chPw);
			System.out.println("************ userid ="+user_id+"; userpw="+user_pw);
			LogicalExpression andGate = Restrictions.and(user_id, user_pw);
			criteria.add(andGate );
			
			if(criteria.uniqueResult() == null){
				result="noMatch";
			}else{			
				result="okMatch";			
			}	
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public Company selectByCompanyId(String companyId) {
		
		Session session = getSession();
		Company company = null;
		
		try {

			Criteria cr = session.createCriteria(Company.class);
			cr.add(Restrictions.eq("company_id", companyId));
			company = (Company) cr.uniqueResult();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return company;
	}

	public String selectIdByUserNameAndEmail(String lastName, String firstName, String email) {
		Session session = getSession();
		String result = "";
		try {

			Criteria criteria= session.createCriteria(Member.class);
	
			criteria.add(Restrictions.eq("last_name", lastName));
			criteria.add(Restrictions.eq("first_name", firstName));
			criteria.add(Restrictions.eq("email", email));
					
			Member member = (Member) criteria.uniqueResult();
			result = member.getUserId();
	
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
				
		return result;
	}
}
