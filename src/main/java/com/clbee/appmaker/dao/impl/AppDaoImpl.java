package com.clbee.appmaker.dao.impl;

import com.clbee.appmaker.dao.AppDao;
import org.apache.ibatis.session.SqlSession;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clbee.appmaker.util.Entity;
import com.clbee.appmaker.model.AppHistory;
import com.clbee.appmaker.model.list.AppList;
import com.clbee.appmaker.model.AppSub;
import com.clbee.appmaker.model.App;
import com.clbee.appmaker.model.list.DownloadList;
import com.clbee.appmaker.model.Member;

@Repository
public class AppDaoImpl implements AppDao {
	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void updateAppSubInfo(AppSub updated, int subNum) {

		Session session = getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			AppSub appSub = session.get(AppSub.class, subNum);
			
			if( updated.getAppSeq() != null )
				appSub.setAppSeq(updated.getAppSeq());
			if( updated.getDepartmentSeq() != null)
				appSub.setDepartmentSeq(updated.getDepartmentSeq());
			if( updated.getUserSeq() != null)
				appSub.setUserSeq(updated.getUserSeq());
				
			session.update(appSub);
	
			tx.commit();
		}catch (Exception e) {
			if (tx!=null) tx.rollback();
		     e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	@Override
	public void updateAppInfo(App updated, int appNum )throws Exception {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		
		System.out.println("updated.getAppContentsGb() = " + updated.getAppContentsGb() );
		try {
			App app = session.get(App.class, appNum);

			if(updated.getAppContentsAmt() != null && !"".equals(updated.getAppContentsAmt() )) {
				app.setAppContentsAmt(updated.getAppContentsAmt());
			}
			if(updated.getAppContentsGb() != null && !"".equals(updated.getAppContentsGb() ))
			{
				app.setAppContentsGb(updated.getAppContentsGb());
			}
			if(updated.getAppName() != null && !"".equals(updated.getAppName() ))
			{app.setAppName(updated.getAppName());}
			if(updated.getApp_resultCode() != null && !"".equals(updated.getApp_resultCode() ))
			{app.setApp_resultCode(updated.getApp_resultCode());}
			if(updated.getAppSize() != null && !"".equals(updated.getAppSize() ))
			{app.setAppSize(updated.getAppSize());}
			if(updated.getChgText() != null && !"".equals(updated.getChgText() ))
			{app.setChgText(updated.getChgText());}
			if(updated.getChgUserGb() != null && !"".equals(updated.getChgUserGb() ))
			{app.setChgUserGb(updated.getChgUserGb());}
			if(updated.getChgUserId() != null && !"".equals(updated.getChgUserId() ))
			{app.setChgUserId(updated.getChgUserId());}
			if(updated.getChgUserSeq() != null && !"".equals(updated.getChgUserSeq() ))
			{app.setChgUserSeq(updated.getChgUserSeq());}
			if(updated.getCompletGb() != null && !"".equals(updated.getCompletGb() ))
			{app.setCompletGb(updated.getCompletGb());}
			if(updated.getCouponGb() != null && !"".equals(updated.getCouponGb() ))
			{app.setCouponGb(updated.getCouponGb());}
	
			//������ ""�� �����..
			if(updated.getCouponNum() != null)
			{app.setCouponNum(updated.getCouponNum());}
			if(updated.getDescriptionText() != null && !"".equals(updated.getDescriptionText() ))
			{app.setDescriptionText(updated.getDescriptionText());}
			if(updated.getDistrGb() != null && !"".equals(updated.getDistrGb() ))
			{app.setDistrGb(updated.getDistrGb());}
			if(updated.getFileName() != null && !"".equals(updated.getFileName() ))
			{app.setFileName(updated.getFileName());}
			if(updated.getIconOrgFile() != null && !"".equals(updated.getIconOrgFile() ))
			{app.setIconOrgFile(updated.getIconOrgFile());}
			if(updated.getIconSaveFile() != null && !"".equals(updated.getIconSaveFile() ))
			{app.setIconSaveFile(updated.getIconSaveFile());}
			if(updated.getLimitDt() != null )
			{app.setLimitDt(updated.getLimitDt());}
			if(updated.getLimitGb() != null && !"".equals(updated.getLimitGb() ))
			{app.setLimitGb(updated.getLimitGb());}
			if(updated.getMemDownAmt() != null && !"".equals(updated.getMemDownAmt() ))
			{app.setMemDownAmt(updated.getMemDownAmt());}
	
			if(updated.getProvisionGb() != null && !"".equals(updated.getProvisionGb()))
				app.setProvisionGb(updated.getProvisionGb());
			if(updated.getInstallGb() != null && !"".equals(updated.getInstallGb()))
				app.setInstallGb(updated.getInstallGb());
			if(updated.getVersionCode() != null && !"".equals(updated.getVersionCode()))
				app.setVersionCode(updated.getVersionCode());
			if(updated.getMemDownCnt() != null && !"".equals(updated.getMemDownCnt() ))
			{app.setMemDownCnt(updated.getMemDownCnt());}
			if(updated.getMemDownEndDt() != null)
			{app.setMemDownEndDt(updated.getMemDownEndDt());}
			if(updated.getMemDownGb() != null && !"".equals(updated.getMemDownGb() ))
			{app.setMemDownGb(updated.getMemDownGb());}
			if(updated.getMemDownStartDt() != null)
			{app.setMemDownStartDt(updated.getMemDownStartDt());}
			if(updated.getNonmemDownAmt() != null && !"".equals(updated.getNonmemDownAmt() ))
			{app.setNonmemDownAmt(updated.getNonmemDownAmt());}
			if(updated.getNonmemDownCnt() != null && !"".equals(updated.getNonmemDownCnt() ))
			{app.setNonmemDownCnt(updated.getNonmemDownCnt());}
			if(updated.getNonmemDownEndDt() != null )
			{app.setNonmemDownEndDt(updated.getNonmemDownEndDt());}
			if(updated.getNonmemDownGb() != null && !"".equals(updated.getNonmemDownGb() ))
			{app.setNonmemDownGb(updated.getNonmemDownGb());}
			if(updated.getNonmemDownStarDt() != null )
			{app.setNonmemDownStarDt(updated.getNonmemDownStarDt());}
			if(updated.getOstype() != null && !"".equals(updated.getOstype() ))
			{app.setOstype(updated.getOstype());}
			if(updated.getRegDt() != null)
			{app.setRegDt(updated.getRegDt());}
			if(updated.getRegGb() != null && !"".equals(updated.getRegGb() ))
			{app.setRegGb(updated.getRegGb());}
			if(updated.getRegUserGb() != null && !"".equals(updated.getRegUserGb() ))
			{app.setRegUserGb(updated.getRegUserGb());}
			if(updated.getRegUserId() != null && !"".equals(updated.getRegUserId() ))
			{app.setRegUserId(updated.getRegUserId());}
			if(updated.getRegUserSeq() != null && !"".equals(updated.getRegUserSeq() ))
			{app.setRegUserSeq(updated.getRegUserSeq());}
			if(updated.getTemplateName() != null && !"".equals(updated.getTemplateName() ))
			{app.setTemplateName(updated.getTemplateName());}
			if(updated.getTemplateSeq() != null && !"".equals(updated.getTemplateSeq() ))
			{app.setTemplateSeq(updated.getTemplateSeq());}
			if(updated.getUseAvailDt() != null )
			{app.setUseAvailDt(updated.getUseAvailDt());}
			if(updated.getUseDisableDt() != null )
			{app.setUseDisableDt(updated.getUseDisableDt());}
	
			if(updated.getUseGb() != null && !"".equals(updated.getUseGb() ))
			{app.setUseGb(updated.getUseGb());}
			if(updated.getVerNum() != null && !"".equals(updated.getVerNum() ))
			{app.setVerNum(updated.getVerNum());}
			if(updated.getUseUserGb() != null && !"".equals(updated.getUseUserGb())) {
				app.setUseUserGb(updated.getUseUserGb());}
			if(updated.getLoginGb() != null &&  !"".equals(updated.getLoginGb()))
				app.setLoginGb(updated.getLoginGb());
			if(updated.getLoginTime() != null && !"".equals(updated.getLoginTime()))
				app.setLoginTime(updated.getLoginTime());
			if(updated.getLogoutTime() != null && !"".equals(updated.getLogoutTime()))
				app.setLogoutTime(updated.getLogoutTime());

			//20180403 : lsy - date update
			if(updated.getChgDt() != null) {
				app.setChgDt(updated.getChgDt());
			}
			if(updated.getDistributeReqDt() != null) {
				app.setDistributeReqDt(updated.getDistributeReqDt());
			}
			if(updated.getChgContentsDt() != null) {
				app.setChgContentsDt(updated.getChgContentsDt());
			}
			if(updated.getDistributeCompletDt() != null) {
				app.setDistributeCompletDt(updated.getDistributeCompletDt());
			}
			if(updated.getDistributeAcceptId() != null) {
				app.setDistributeAcceptId(updated.getDistributeAcceptId());
			}
			if(updated.getDistributeRequestId() != null) {
				app.setDistributeRequestId(updated.getDistributeRequestId());
			}
			//20180403 : lsy - date update - end
			
			session.update(app);
			tx.commit();
		}catch (Exception e) {
			if( tx!= null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();			
		}
	}

	@Override
	public int insertAppInfo( App app ){
		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(app);
			tx.commit();
		}catch(Exception e) {
			if( tx!= null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();	
		}
		return app.getAppSeq();
		/*session.createCriteria(App.class);*/
	}

	@Override
	public int insertAppHistoryInfo(AppHistory app ){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			session.save(app);
			tx.commit();
		}catch(Exception e) {
			if( tx!= null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();	
		}

		return app.getAppSeq();
	}

	@Override
	public List<App> selectList(int startNo, String user_id ){
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = null;
		List<App> list = null;
		
		try{
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM App E "
					+ "WHERE E.user_id = :id ORDER BY E.last_update DESC ")
					.setFirstResult(startNo).setMaxResults(10)
					.setParameter("id", user_id);
			list = query.list();
			tx.commit();
		}catch(Exception e) {
			if( tx!= null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();	
		}

		return list;
	}

	@Override
	public App selectByStoreId( String storeBundleId ) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = null;
		List<App> list = null;
		
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(App.class);
			cr.add(Restrictions.eq("storeBundleId", storeBundleId));
			list = cr.list();
			tx.commit();
		}catch(Exception e) {
			if( tx!= null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();	
		}

		
		if(list.size() == 0) return null;
		else return list.get(0);
	}

	@Override
	public App selectById( int appNum ) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = null;
		
		App app = null;
		try {
			tx= session.beginTransaction();
	
			Criteria cr = session.createCriteria(App.class);
			cr.add(Restrictions.eq("appSeq", appNum));
			app = (App) cr.uniqueResult();
	
			tx.commit();
		}catch(Exception e) {
			if( tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}

		return app;
	}
	
	@Override
	public List<App> selectByUserId( String user_id) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = null;
		List<App> list = null;
		
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(App.class);
			cr.add(Restrictions.eq("user_id", user_id));
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
	public List<App> selectByUserIdAndIdenty( String user_id, String bundle_identy) {

		Session session = getSession();
		Transaction tx = null;
		
		List<App> list = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(App.class);
	
			Criterion username = Restrictions.eq("user_id", user_id);
			Criterion password = Restrictions.eq("store_bundle_id", bundle_identy);
	
			LogicalExpression andGate = Restrictions.and(username, password);
			cr.add( andGate );
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
	public int getListCount( String user_id ) {
		Session session = getSession();

		Transaction tx = null;
		Number number = null;
		
		try {
			tx = session.beginTransaction();
			// �ش� ���̵� User�� �۵�� ����
			number = ((Number) session.createCriteria(App.class).add(Restrictions.eq("user_id", user_id)).
					setProjection(Projections.rowCount()).uniqueResult());
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		return number.intValue();
	}

	@Override
	public void updateAppByIdentifier(App updated, String store_bundle_id) {
		Session session = getSession();

		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			/*session.createCriteria(arg0)*/
	
			String hql = "UPDATE App set ver_num = :version,"
					+"app_resultCode = :code,"
					+"chg_dt = :update"
					+" WHERE store_bundle_id = :identifier";
	
			System.out.println("updated.getVerNum() = " + updated.getVerNum());
			System.out.println("updated.getApp_resultCode() = " + updated.getApp_resultCode());
			System.out.println("store_bundle_id = " + store_bundle_id);
	
			Query query = session.createQuery(hql)
					.setParameter("version", updated.getVerNum())
					.setParameter("code", updated.getApp_resultCode())
					.setParameter("update", updated.getChgDt())
					.setParameter("identifier", store_bundle_id);
	
			query.executeUpdate();
	
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
	}

	@Override
	public Object selectByBundleId(Entity param) {
		return sqlSession.selectList("selectAppByBundleIdAndOstype",param);
	}

	@Override
	public int getSeqAfterInsertAppInfo(App app) {
		Session session = getSession();
		Transaction t = null;
		List<BigInteger> appList = null;
		int appSeq =0;
		try {
			t=session.beginTransaction();
	
			System.out.println("app.getUseUserGb() = " + app.getUseUserGb());
			String sql = "CALL SP_INSERT_APP (:regGb, :appName, :appContentsAmt, :appContentsGb, :fileName ";
			sql+=", :ostype, :verNum, :storeBundleId, :provisionGb, :descriptionText ";
			sql+=", :iconOrgFile, :iconSaveFile, :templateName, :templateSeq, :useGb";
			sql+=", :completGb, :memDownGb, :couponGb, :nonmemDownGb, :installGb, :versionCode, :limitGb";
			sql+=", :regUserSeq, :regUserId, :regUserGb ";
			sql+=", :chgUserSeq, :chgUserId, :chgUserGb ";
			sql+=", :useUserGb, :loginTime, :logoutTime, :loginGb )";
	
			Query callStoredProcedure_MYSQL = session.createSQLQuery(sql);
		
			callStoredProcedure_MYSQL.setString("regGb", app.getRegGb());
			callStoredProcedure_MYSQL.setString("appName", app.getAppName());
			callStoredProcedure_MYSQL.setString("appContentsAmt", app.getAppContentsAmt());
			callStoredProcedure_MYSQL.setString("appContentsGb", app.getAppContentsGb());
			callStoredProcedure_MYSQL.setString("fileName", app.getFileName());
			
			callStoredProcedure_MYSQL.setString("ostype", app.getOstype());
			callStoredProcedure_MYSQL.setString("verNum", app.getVerNum());
			callStoredProcedure_MYSQL.setString("storeBundleId", app.getStoreBundleId());
			callStoredProcedure_MYSQL.setString("provisionGb", app.getProvisionGb());
			callStoredProcedure_MYSQL.setString("descriptionText", app.getDescriptionText());
			
			callStoredProcedure_MYSQL.setString("iconOrgFile", app.getIconOrgFile());
			callStoredProcedure_MYSQL.setString("iconSaveFile", app.getIconSaveFile());
			callStoredProcedure_MYSQL.setString("templateName", app.getTemplateName());
			//callStoredProcedure_MYSQL.setInteger("templateSeq", app.getTemplateSeq());
			callStoredProcedure_MYSQL.setParameter("templateSeq", app.getTemplateSeq());
			callStoredProcedure_MYSQL.setString("useGb", app.getUseGb());
			
			callStoredProcedure_MYSQL.setString("completGb", app.getCompletGb());
			callStoredProcedure_MYSQL.setString("memDownGb", app.getMemDownGb());
			callStoredProcedure_MYSQL.setString("couponGb", app.getCouponGb());
			callStoredProcedure_MYSQL.setString("nonmemDownGb", app.getNonmemDownGb());
			callStoredProcedure_MYSQL.setString("installGb", app.getInstallGb());
			callStoredProcedure_MYSQL.setString("versionCode", app.getVersionCode());
			callStoredProcedure_MYSQL.setString("limitGb", app.getLimitGb());
			
			callStoredProcedure_MYSQL.setInteger("regUserSeq", app.getRegUserSeq());
			callStoredProcedure_MYSQL.setString("regUserId", app.getRegUserId());
			callStoredProcedure_MYSQL.setString("regUserGb", app.getRegUserGb());
			
			callStoredProcedure_MYSQL.setInteger("chgUserSeq", app.getRegUserSeq());
			callStoredProcedure_MYSQL.setString("chgUserId", app.getRegUserId());
			callStoredProcedure_MYSQL.setString("chgUserGb", app.getRegUserGb());
			callStoredProcedure_MYSQL.setString("useUserGb", app.getUseUserGb());
		    callStoredProcedure_MYSQL.setString("loginTime", app.getLoginTime());
		    callStoredProcedure_MYSQL.setString("logoutTime", app.getLogoutTime());
		    callStoredProcedure_MYSQL.setString("loginGb", app.getLoginGb());
		    
			/* callStoredProcedure_MSSQL.list() will execute stored procedure and return the value */
			appList = callStoredProcedure_MYSQL.list();
			System.out.println("@@@@@@@@@@@@"+appList.get(0).intValue());
			
			appSeq = appList.get(0).intValue();
			t.commit();
		}catch (Exception e) {
			if(t != null) t.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}

		return appSeq;
	}
  	@Override
	public int getListCount(AppList appList, Member Member) {
		Session session = getSession();

		Transaction tx = null;
		Number number = null;
		
		try {
			tx = session.beginTransaction();
			Criteria appCr = session.createCriteria(App.class, "App"); //.createCriteria(App.class);
			appCr.createAlias("regMember", "Member");
			if(Member.getUserGb()!=null&&!"5".equals(Member.getUserGb())){
				appCr.add(Restrictions.or(Restrictions.and(Restrictions.eq("Member.companyGb", "2"), Restrictions.eq("Member.userSeq", Member.getUserSeq())),Restrictions.and(Restrictions.eq("Member.companyGb", "1"), Restrictions.eq("Member.companySeq", Member.getCompanySeq()))));			
			}
			if(appList.getIsAvailable() !=null && "true".equals(appList.getIsAvailable())) {
				appCr.add(Restrictions.or(
						Restrictions.eq("limitGb","1"),
						Restrictions.eq("useGb","1"))
						);
			}

			if(appList.getSearchValue()!=null&&appList.getSearchValue().length()>0){
				if(appList.getSearchType()!=null&&appList.getSearchType().length()>0){
					appCr.add(Restrictions.and(Restrictions.like(appList.getSearchType(), "%"+appList.getSearchValue()+"%")));
				}else if((appList.getSearchType()==null||appList.getSearchType().length()==0)||"".equals(appList.getSearchType())){
					appCr.add(Restrictions.and(Restrictions.or(Restrictions.like("appName", "%"+appList.getSearchValue()+"%"), Restrictions.like("ostype", "%"+appList.getSearchValue()+"%"))));
				}
			}
			number = (Number)appCr.setProjection(Projections.rowCount()).uniqueResult();
	
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		return number.intValue();
	}

  	@Override
	public List<App> selectList(AppList appList, Member Member) {
		Session session = getSession();

		Transaction tx = null;
		List<App> list = null;
		
		try {
			tx = session.beginTransaction();
	
			Criteria appCr = session.createCriteria(App.class, "App"); //.createCriteria(App.class);
			appCr.createAlias("regMember", "Member");
			if(Member.getUserGb()!=null&&!"255".equals(Member.getUserGb())){
				appCr.add(
					Restrictions.or(
						Restrictions.and(
							Restrictions.eq("Member.companyGb", "2"),
							Restrictions.eq("Member.userSeq", Member.getUserSeq())
						),
						Restrictions.and(
							Restrictions.eq("Member.companyGb", "1"),
							Restrictions.eq("Member.companySeq", Member.getCompanySeq())
						)
					)
				);
			}
			if(appList != null){
				if(appList.getIsAvailable() !=null && "true".equals(appList.getIsAvailable())) {
					appCr.add(Restrictions.or(
							Restrictions.eq("limitGb","1"),
							Restrictions.eq("useGb","1"))
					);
				}
			}
			if( appList != null) {	/* appList�� Null�ΰ��� �ƹ��� ���� ����  */
				if(appList.getSearchValue()!=null&&appList.getSearchValue().length()>0){
					if(appList.getSearchType()!=null&&appList.getSearchType().length()>0){
						appCr.add(Restrictions.and(Restrictions.like(appList.getSearchType(), "%"+appList.getSearchValue()+"%")));
					}else if((appList.getSearchType()==null||appList.getSearchType().length()==0)||"".equals(appList.getSearchType())){
						System.out.println("appList.getSearchType()====="+appList.getSearchType());
						System.out.println("appList.getSearchValue()====="+appList.getSearchValue());
						appCr.add(Restrictions.and(Restrictions.or(Restrictions.like("appName", "%"+appList.getSearchValue()+"%"), Restrictions.like("ostype", "%"+appList.getSearchValue()+"%"))));
					}
				}
				appCr.setFirstResult(appList.getStartNo());
				appCr.setMaxResults(appList.getMaxResult());
				appCr.addOrder(Order.desc("chgDt"));
				appCr.addOrder(Order.desc("useGb"));
			}
			list =  (List<App>)appCr.list();
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
	public App selectForUpdate(App app, Member Member) {
		Session session = getSession();

		Transaction tx = null;
		App selected = null;
		
		try {
			tx= session.beginTransaction();
	
			Criteria appCr = session.createCriteria(App.class, "app"); //.createCriteria(App.class);
			appCr.createAlias("regMember", "Member");
			appCr.add(Restrictions.eq("App.appSeq", app.getAppSeq()));
			if(Member.getUserGb()!=null&&!"255".equals(Member.getUserGb())){
				appCr.add(
					Restrictions.or(
						Restrictions.and(
							Restrictions.eq("Member.companyGb", "2"), 
							Restrictions.eq("Member.userSeq", Member.getUserSeq())
						),
						Restrictions.and(
							Restrictions.eq("Member.companyGb", "1"),
							Restrictions.eq("Member.companySeq", Member.getCompanySeq())
						)
					)
				);
			}
			selected = (App) appCr.uniqueResult();
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		return selected;
	}

	@Override
	public int getListCount(DownloadList downloadList, Member Member) {
		Session session = getSession();

		Transaction tx = null;
		List<App> list = null;
		Number number = null;
	
		try {
			tx = session.beginTransaction();		
	
			Query query = session.createQuery("FROM App E "
					//+ "WHERE E.user_id = :id ORDER BY E.last_update DESC ")
					+ " WHERE 1=1 "
					//+ AndVal
					+ " ORDER BY E.regDt DESC ");
					//.setFirstResult(templateList.getStartNo())
					//.setMaxResults(10);
					//.setParameter("id", user_id);
			
					
			list = query.list();
			System.out.println("End in Select List");
			number = list.size();	
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		return number.intValue();
	}

	@Override
	public List<?> selectList(DownloadList downloadList, Member Member) {
		Session session = getSession();

		Transaction tx = null;
		List<App> list = null;
		
		try {
			tx = session.beginTransaction();		
	
			Query query = session.createQuery("FROM App E "
					//+ "WHERE E.user_id = :id ORDER BY E.last_update DESC ")
					+ " WHERE 1=1 "
					//+ AndVal
					+ " ORDER BY E.regDt DESC ")
					//.setFirstResult(startNo)
					.setFirstResult(downloadList.getStartNo())
					.setMaxResults(10);
					//.setParameter("id", user_id);
					
			list = query.list();
			System.out.println("End in Select List");
	
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
	public List selectList(Entity param) {
		if(param.get("menuFunction") != null && !param.get("menuFunction").equals("")) {
			if(param.get("menuFunction").toString().indexOf("131") != -1 && param.get("menuFunction").toString().indexOf("131") >= 0) {
				param.setValue("check131", "Y");
			}else {
				param.setValue("check131", "N");
			}
		}else {
			param.setValue("check131", "N");
		}
		
		if(param.get("menuFunction") != null && !param.get("menuFunction").equals("")) {
			if(param.get("menuFunction").toString().indexOf("132") != -1 && param.get("menuFunction").toString().indexOf("132") >= 0) {
				param.setValue("check132", "Y");
			}else {
				param.setValue("check132", "N");
			}
		}else {
			param.setValue("check132", "N");
		}
		
		if(param.get("menuFunction") != null && !param.get("menuFunction").equals("")) {
			if(param.get("menuFunction").toString().indexOf("134") != -1 && param.get("menuFunction").toString().indexOf("134") >= 0) {
				param.setValue("check134", "Y");
			}else {
				param.setValue("check134", "N");
			}
		}else {
			param.setValue("check134", "N");
		}
		return sqlSession.selectList("downSelectList",param);
	}

	@Override
	public List getSelectListCount(Entity param) {
		//	20180523 : lsy - 그룹권한 추가
		if(param.get("menuFunction") != null && !param.get("menuFunction").equals("")) {
			if(param.get("menuFunction").toString().indexOf("131") != -1 && param.get("menuFunction").toString().indexOf("131") >= 0) {
				param.setValue("check131", "Y");
			}else {
				param.setValue("check131", "N");
			}
		}else {
			param.setValue("check131", "N");
		}
		
		if(param.get("menuFunction") != null && !param.get("menuFunction").equals("")) {
			if(param.get("menuFunction").toString().indexOf("132") != -1 && param.get("menuFunction").toString().indexOf("132") >= 0) {
				param.setValue("check132", "Y");
			}else {
				param.setValue("check132", "N");
			}
		}else {
			param.setValue("check132", "N");
		}
		
		if(param.get("menuFunction") != null && !param.get("menuFunction").equals("")) {
			if(param.get("menuFunction").toString().indexOf("134") != -1 && param.get("menuFunction").toString().indexOf("134") >= 0) {
				param.setValue("check134", "Y");
			}else {
				param.setValue("check134", "N");
			}
		}else {
			param.setValue("check134", "N");
		}
		return sqlSession.selectList("downSelectListCount", param);
	}

	@Override
	public List getSelectCouponList(Entity param) {
		return sqlSession.selectList("downSelectCouponList", param);
	}

	@Override
	public String getSelectTodayDate() {
		return (String) sqlSession.selectOne("downTodayDate", "");
	}

	@Override
	public List getCountOfIdenticalCouponNumForAll( Entity param) {
		return sqlSession.selectList("selectIfAnyIdenticalCouponNumForAll", param);
	}

	@Override
	public List getRowIsCompletedByBundleId( Entity param) {
		return sqlSession.selectList("getRowIsCompletedByBundleId", param);
	}

	@Override
	public List getListNotComplte( Member Member ) {
		Session session = getSession();
		Transaction tx = null;
		List list = null;
		
		try {
			tx = session.beginTransaction();
	
			Criteria cr = session.createCriteria(App.class);
			cr.createAlias("regMember", "Member");
			cr.add(
				Restrictions.or(
					Restrictions.and(
						Restrictions.eq("Member.companyGb", "2"), 
						Restrictions.eq("Member.userSeq", Member.getUserSeq())
					),
					Restrictions.and(
						Restrictions.eq("Member.companyGb", "1"),
						Restrictions.eq("Member.companySeq", Member.getCompanySeq())
					)
				)
			);
			cr.add(Restrictions.eq("useGb", "1"));
			cr.add(Restrictions.eq("limitGb", "2"));
			cr.addOrder(Order.desc("chgDt"));
	
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
	public void deleteAppInfo(int appSeq) {
		Session session = getSession();

		Transaction tx = null;
		
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(App.class);
			cr.add(
				Restrictions.eq("appSeq", appSeq)
			);
			App app = (App)cr.uniqueResult();
			session.delete(app);
			
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
	}

	@Override
	public void deleteAppHistoryInfo(int appSeq) {
		Session session = getSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(AppHistory.class);
			cr.add(
				Restrictions.eq("appSeq", appSeq)
			);
			App app = (App)cr.uniqueResult();
			session.delete(app);
			
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
	}
	
	@Override
	public List<AppSub> selectAppSubList(int appSeq) {
		Session session = getSession();

		Transaction tx = null;
		List list = null;
		try {
			tx = session.beginTransaction();
	
			Criteria cr = session.createCriteria(AppSub.class);
			cr.add(
				Restrictions.eq("appSeq", appSeq)
			);
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
	public int insertAppSubInfo( AppSub AppSub ) {
		Session session = getSession();

		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			session.save(AppSub);
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}

		return AppSub.getAppSeq();
	}

	@Override
	public void deleteAppSubInfo( AppSub AppSub ) {
		Session session = getSession();

		Transaction tx = null;
		
		
		try {
			tx = session.beginTransaction();
	
			String hql = "DELETE FROM AppSub T " + 
		             "WHERE T.appSeq = :appSeq ";
			
			Query query = session.createQuery(hql);
			query.setParameter("appSeq", AppSub.getAppSeq());
			query.executeUpdate();
	
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
	}
	
	@Override
	public List<App> getNotPermmitList(int companySeq, Integer[] useA, String searchValue, String searchType) {
		Session session = getSession();

		Transaction tx = null;
		List list = null;
		
		try {
			tx = session.beginTransaction();	
	
			Criteria cr = session.createCriteria(App.class);
			Criteria alias = cr.createAlias("regMember", "Member");
			
			alias.add(
				Restrictions.eq("Member.companySeq", companySeq)
			);
			
			if(useA != null && useA.length > 0) {
				cr.add(					
					Restrictions.not(
						Restrictions.in("appSeq", useA)
					)
				);
			}
	
			if(searchValue != null && searchType != null) {
				switch(Integer.parseInt(searchType)) {
					case 1:
						cr.add(Restrictions.like("", "%"+searchValue+"%"));
						break;
					case 2: 
						cr.add(Restrictions.like("", "%"+searchValue+"%"));
						break;
					case 3: 
						cr.add(Restrictions.like("", "%"+searchValue+"%"));
						break;
					case 4: 
						cr.add(Restrictions.like("", "%"+searchValue+"%"));
						break;
				}
			}
	
			cr.addOrder(Order.desc("regDt"));
	
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
	public List<App> getPermitList(int companySeq, Integer[] useA) {
		Session session = getSession();

		Transaction tx = null;
		List list = null;
		
		try {
			tx = session.beginTransaction();
	
			Criteria cr = session.createCriteria(App.class);
			Criteria alias = cr.createAlias("regMember", "Member");
	
			alias.add(
				Restrictions.eq("Member.companySeq", companySeq)
			);
	
			if(useA != null && useA.length > 0) {
				cr.add(					
					Restrictions.in("appSeq", useA)
				);
			}else {
				return null;
			}
	
			cr.addOrder(Order.desc("regDt"));
	
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
	public int checkIfAvailableAppByBundleId( int userSeq, String ostype, String storeBundleId ) {
		Session session = getSession();

		Transaction tx = null;
		List<App> list = null;
		
		try {
			tx = session.beginTransaction();
	
			Criteria cr = session.createCriteria(App.class,"App");
			cr.createAlias("App.AppSub", "Sub", JoinType.LEFT_OUTER_JOIN);
	
			if("4".equals(ostype)){
				System.out.println("i'm Android@@@@@@@@@@@@@@@@@@@@@@");
				cr.add(
					Restrictions.or(
						Restrictions.and(
							Restrictions.eq("storeBundleId", storeBundleId),
							Restrictions.eq("useUserGb", "1"),
							Restrictions.eq("ostype", "4")
						),
						Restrictions.and(
							Restrictions.eq("storeBundleId", storeBundleId),
							Restrictions.eq("useUserGb", "2"),
							Restrictions.eq("ostype", "4"),
							Restrictions.eq("Sub.userSeq", userSeq)
						)
					)
				);
			}else{
				System.out.println("i'm iOS@@@@@@@@@@@@@@@@@@@@@@");
				cr.add(
						Restrictions.or(
							Restrictions.and(
								Restrictions.eq("storeBundleId", storeBundleId),
								Restrictions.eq("useUserGb", "1"),
								Restrictions.or(
										Restrictions.eq("ostype", "1"),
										Restrictions.eq("ostype", "2"),
										Restrictions.eq("ostype", "3")										
								)
							),
							Restrictions.and(
								Restrictions.eq("storeBundleId", storeBundleId),
								Restrictions.eq("useUserGb", "2"),
								Restrictions.or(
										Restrictions.eq("ostype", "1"),
										Restrictions.eq("ostype", "2"),
										Restrictions.eq("ostype", "3")										
								),
								Restrictions.eq("Sub.userSeq", userSeq)
							)
						)
					);
			}
	
			list = cr.list();
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}

		if(list.size() > 0) {
			return 5000;
		}else if(list.size() == 0){
			return 5001;
		}else {
			return 9999;
		}
	}

	@Override
	public List<App> getListIsAvailableByCompanySeq(int companySeq) {
		Session session = getSession();

		Transaction tx = null;
		List<App> list = null;
		
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(App.class,"App");
			cr.createAlias("App.regMember", "Member", JoinType.LEFT_OUTER_JOIN);
	
			cr.add(
				Restrictions.and(
					Restrictions.eq("Member.companySeq", companySeq),
					Restrictions.eq("useGb","1"),
					Restrictions.eq("completGb", "1"),
					Restrictions.eq("limitGb", "2"),
					Restrictions.eq("app_resultCode", "1")
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
		return list;
	}

	@Override
	public App selectByBundleIdAndOstype(String osType, String storeBundleId) {
		Session session = getSession();

		Transaction tx = null;
		App app  = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(App.class);
			
			cr.add(
				Restrictions.eq("storeBundleId", storeBundleId)
			);
			
			if(!"4".equals(osType)) {
				cr.add(
					Restrictions.or(
						Restrictions.eq("ostype", "1"),
						Restrictions.eq("ostype", "2"),
						Restrictions.eq("ostype", "3")
					)
				);
			}
			else {
				cr.add(Restrictions.eq("ostype", "4"));
			}
			cr.addOrder( Order.desc("verNum") );
			cr.setFirstResult(0);
			cr.setMaxResults(1);
			app = (App) cr.uniqueResult();
			
			Hibernate.initialize(app.getAppSubList());
			tx.commit();

		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
				
		return app;
	}

	@Override
	public void deleteAppSubAppSeqInfo(int appSeq) {
		Session session = getSession();

		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(AppSub.class);
			cr.add(
				Restrictions.eq("appSeq", appSeq)
			);
			App app = (App) cr.uniqueResult();
			session.delete(app);
			
			tx.commit();
		} catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		} finally {
			session.close();
		}
	}

  	@Override
	public List<App> selectAppForHistory(String DBName, String store_bundle_id) {

		var list = new ArrayList<Map<String,String>>();
		var paramMap = new HashMap<String, String>();

		paramMap.put("store_bundle_id", store_bundle_id);
		list.add(paramMap);
		
		return sqlSession.selectList(DBName, list);
	}

	@Override
	public App selectAppBySeq( String DBName, int app_seq){
		return sqlSession.selectOne(DBName, app_seq);
	}
	@Override
	public App selectBeforAppInfoBySeqForNewVersion(String DBName, int app_seq) {
		return sqlSession.selectOne(DBName, app_seq);
	}
	@Override
	public int insertNewVersionAppInfo(String DBName, App newVersionAppInfo) {
		sqlSession.insert(DBName, newVersionAppInfo);
		return newVersionAppInfo.getAppSeq();
	}
	@Override
	public App selectAppByAppSeqForMakeJobTicket(String DBName, int app_seq) {
		return sqlSession.selectOne(DBName, app_seq);
	}
	@Override
	public int duplicateVerCheck(String DBName, Entity param) {
		return sqlSession.selectOne(DBName, param);
	}
	@Override
	public String selectAppVersionMax(String DBName, int app_seq) {
		return sqlSession.selectOne(DBName, app_seq);
	}
}