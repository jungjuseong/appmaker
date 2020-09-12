package com.clbee.appmaker.dao.impl;


import com.clbee.appmaker.dao.InAppCategoryDao;
import com.clbee.appmaker.model.InAppCategory;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;


@Repository
public class InAppCategoryDaoImpl implements InAppCategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public int insertInAppInfo(InAppCategory category) {
		
		Session session = getSession();
		Transaction tx = null;
		int categorySeq = 0;
		
		try {
			tx = session.beginTransaction();
	
			Query callStoredProcedure_MYSQL = session.createSQLQuery("CALL SP_INSERT_INAPPCATEGORY (:storeBundleId, :depth, :categoryName, :categoryParent, :regUserSeq, :regUserId, :regUserGb, :regDt, :chgUserSeq, :chgUserId, :chgUserGb, :chgDt)");
			callStoredProcedure_MYSQL.setString("storeBundleId", category.getStoreBundleId());
			callStoredProcedure_MYSQL.setString("depth", category.getDepth());
			callStoredProcedure_MYSQL.setString("categoryName", category.getCategoryName());
			callStoredProcedure_MYSQL.setLong("categoryParent", category.getCategoryParent());
			callStoredProcedure_MYSQL.setLong("regUserSeq", category.getRegUserSeq());
			callStoredProcedure_MYSQL.setString("regUserId", category.getRegUserId());
			callStoredProcedure_MYSQL.setString("regUserGb", category.getRegUserGb());
			callStoredProcedure_MYSQL.setDate("regDt", category.getRegDt());
			callStoredProcedure_MYSQL.setLong("chgUserSeq", category.getChgUserSeq());
			callStoredProcedure_MYSQL.setString("chgUserId", category.getChgUserId());
			callStoredProcedure_MYSQL.setString("chgUserGb", category.getChgUserGb());
			callStoredProcedure_MYSQL.setDate("chgDt", category.getChgDt());
	
			/* callStoredProcedure_MSSQL.list() will execute stored procedure and return the value */
			List<BigInteger> categoryList = callStoredProcedure_MYSQL.list();
			//ystem.out.println("@@@@@@@@@@@@"+companyList.get(0).intValue());
			
			categorySeq = categoryList.get(0).intValue();
	
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		
		return categorySeq;
	}

	@Override
	public List<InAppCategory> selectInAppList(InAppCategory category) {

		Session session = getSession();
		Transaction tx = null;
		List<InAppCategory> result = null;

		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(InAppCategory.class);
			
			Criterion appSeq 		  = Restrictions.eq("storeBundleId", category.getStoreBundleId());
			Criterion categoryParent  = Restrictions.eq("categoryParent", 0);
			
			LogicalExpression andGate = Restrictions.and(appSeq, categoryParent);
			cr.add( andGate );	
			result = cr.list(); 
	
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
	public void updateInAppInfo(InAppCategory category) {

		Session session = getSession();
		Transaction tx = null;
		
		
		try {
			tx = session.beginTransaction();
					
			String hql = "UPDATE InAppCategory set "
					+ "categoryName  	   = :categoryName, "
					+ "depth	 		   = :depth, "
					/*+ "categoryParent      = :categoryParent, "*/
					+ "chgUserSeq 	       = :chgUserSeq, "
					+ "chgUserId 		   = :chgUserId, "
					+ "chgUserGb 		   = :chgUserGb "
					+" WHERE categorySeq   = :categorySeq";
	
			Query query1 = session.createQuery(hql)
					.setParameter("categoryName", category.getCategoryName())
					.setParameter("depth", category.getDepth())
					/*.setParameter("categoryParent", category.getCategoryParent())*/
					.setParameter("chgUserSeq", category.getChgUserSeq())
					.setParameter("chgUserId", category.getChgUserId())
					.setParameter("chgUserGb", category.getChgUserGb())
					.setParameter("categorySeq", category.getCategorySeq());
	
			query1.executeUpdate();
			
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
	}

	@Override
	public void deleteInAppInfo(InAppCategory category) {
		Session session = getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			int cateGubun = category.getCategoryParent();	
			if(cateGubun == 0){
				String hql = "Delete From InAppCategory "
						+" WHERE categorySeq   = :categorySeq "
						+" AND   storeBundleId	   = :storeBundleId ";
				
				Query query1 = session.createQuery(hql)				
						.setParameter("categorySeq", category.getCategorySeq())		
						.setParameter("storeBundleId", category.getStoreBundleId());
				query1.executeUpdate();
				String hql2 = "Delete From InAppCategory "
						+" WHERE categoryParent   = :categoryParent "
						+" AND   storeBundleId	  = :storeBundleId ";
				
				Query query2 = session.createQuery(hql2)				
						.setParameter("categoryParent", category.getCategorySeq())		
						.setParameter("storeBundleId", category.getStoreBundleId());	
						
				query2.executeUpdate();
				tx.commit();
			}
			if(cateGubun > 0){
				String hql = "Delete From InAppCategory "
						+" WHERE categorySeq   = :categorySeq "
						+" AND   storeBundleId = :storeBundleId ";
				
				Query query1 = session.createQuery(hql)				
						.setParameter("categorySeq", category.getCategorySeq())		
						.setParameter("storeBundleId", category.getStoreBundleId());	
						
				query1.executeUpdate();
				tx.commit();
			}
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
	}

	@Override
	public Object[] selectInAppList2(InAppCategory category) {

		Session session = getSession();
		Transaction tx = null;
		Object[] result = null;

		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(InAppCategory.class);
			
			Criterion categoryParent = Restrictions.eq("categoryParent", category.getCategoryParent());
			Criterion storeBundleId  = Restrictions.eq("storeBundleId", category.getStoreBundleId());
			
			LogicalExpression andGate = Restrictions.and(categoryParent, storeBundleId);
			cr.add( andGate );		
			
			result = cr.list().toArray(); 
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
	public InAppCategory findByCustomInfo( String DBName, int intValue){
		Session session = getSession();
		Transaction tx = null;
		InAppCategory InAppCategory = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(InAppCategory.class);
			Criterion user = Restrictions.eq(DBName, intValue);
			
			cr.add(user);
			InAppCategory = (InAppCategory) cr.uniqueResult();
	
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}

		return InAppCategory;
	}
	
	@Override
	public InAppCategory findByCustomInfo( String DBName, String value){

		Session session = getSession();
		Transaction tx = null;
		InAppCategory InAppCategory = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(InAppCategory.class);
			Criterion user = Restrictions.eq(DBName, value);
			
			cr.add(user);
			InAppCategory = (InAppCategory) cr.uniqueResult();
			
			
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}

		return InAppCategory;
	}
	
	@Override
	public List<InAppCategory> getListInAppCategoryForOneDepth( String DBName, String storeBundleId){

		Session session = getSession();
		Transaction tx = null;
		List<InAppCategory> list = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(InAppCategory.class);
			Criterion user = Restrictions.eq(DBName, storeBundleId);
			
			cr.add(user);
			cr.add(Restrictions.eq("depth", "1"));
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
	public List<InAppCategory> getListInAppCategory( String DBName, String storeBundleId){

		Session session = getSession();
		Transaction tx = null;
		List<InAppCategory> list = null;
		
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(InAppCategory.class);
			Criterion user = Restrictions.eq(DBName, storeBundleId);
			
			cr.add(user);
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
	public int categoryIsDuplicated( String storeBundleId, String categoryName ) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = null;
		List list = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(InAppCategory.class);
			cr.add(
				Restrictions.and(
					Restrictions.eq("storeBundleId", storeBundleId),
					Restrictions.eq("categoryName", categoryName)
				)
			);
			
			list = cr.list();
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		
		
		System.out.println("list = " + list);
		if( list != null) {
			if(list.size() == 0) {
				return 1;	
			}else if( list.size() > 0) {
				return 0;
			}else {
				return -1;
			}
		}else {
			return 1;
		}
	}
}
