package com.clbee.appmaker.dao.impl;

import com.clbee.appmaker.dao.InAppDao;
import com.clbee.appmaker.model.*;
import com.clbee.appmaker.model.list.InAppList;
import org.apache.ibatis.session.SqlSession;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class InAppDaoImpl implements InAppDao {
	
	private final SessionFactory sessionFactory;

	InAppDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public InApp findByCustomInfo(String DBName, int intValue) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		InApp inApp = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(InApp.class);
			Criterion user = Restrictions.eq(DBName, intValue);
			
			cr.add(user);
			inApp = (InApp) cr.uniqueResult();
			
			Hibernate.initialize(inApp.getInAppSub());
			
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		return inApp;
	}


	@Override
	public InApp findByCustomInfo(String DBName, String value) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		InApp inApp = null;

		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(InApp.class);
			Criterion user = Restrictions.eq(DBName, value);
			
			cr.add(user);
			inApp = (InApp) cr.uniqueResult();
			Hibernate.initialize(inApp.getInAppSub());
			
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}

		return inApp;
	}

	@Override
	public List findListByCustomInfo(String DBName, String value) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<InApp> list = null;
		
			try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(InApp.class);
			Criterion user = Restrictions.eq(DBName, value);
			
			cr.add(user);
			list = cr.list();
	
			for(InApp inApp : list) {
				Hibernate.initialize(inApp.getInAppSub());
			}
			
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
	public List findListByCustomInfo(String DBName, int value) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<InApp> list = null;
		
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(InApp.class);
	
			cr.add(
				Restrictions.and(
					Restrictions.eq(DBName, value),
					Restrictions.eq("completGb", "1"),
					Restrictions.eq("useGb", "1"),
					Restrictions.eq("limitGb", "2")
				)
			);
	
			list = cr.list();
			for(InApp inApp : list) {
				Hibernate.initialize(inApp.getInAppSub());
			}
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
	public List<InApp> getListInApp(String DBName, String storeBundleId, int userSeq) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<InApp> list = null;
		try {
			tx = session.beginTransaction();
	
			Criteria cr = session.createCriteria(InApp.class,"inApp");
			cr.createAlias("inApp.inappSub", "Sub", JoinType.LEFT_OUTER_JOIN);
			
			cr.add(
				Restrictions.or(
					Restrictions.and(
						Restrictions.eq("limitGb","2"),
						Restrictions.eq("completGb", "1"),
						Restrictions.eq(DBName, storeBundleId),
						Restrictions.eq("useGb", "1"),
						Restrictions.eq("useUserGb", "1")
					),
					Restrictions.and(
						Restrictions.eq("limitGb","2"),
						Restrictions.eq("completGb", "1"),
						Restrictions.eq(DBName, storeBundleId),
						Restrictions.eq("useGb", "1"),
						Restrictions.eq("useUserGb", "2"),
						Restrictions.eq("Sub.userSeq", userSeq)
					)
				)
			);
		
			list = cr.list();
	
			for(InApp inApp : list) {
				Hibernate.initialize(inApp.getInAppSub());
			}
	
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
	public List<InApp> getListInApp(String DBName, String value) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<InApp> list = null;

		try {
			tx = session.beginTransaction();
	
			Criteria cr = session.createCriteria(InApp.class);
			Criterion user = Restrictions.eq(DBName, value);
	
			cr.add(user);
			list = cr.list();
	
			for(InApp inApp : list) {
				Hibernate.initialize(inApp.getInAppSub());
			}
	
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
	public int getListCntByBundleId(InApp vo, InAppList inAppList, Member member) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Number number = null;
		try {
			tx = session.beginTransaction();
			
			
			System.out.println("storeBundleId for InAP = " + vo.getStoreBundleId());
			Criteria appCr = session.createCriteria(InApp.class); //.createCriteria(AppVO.class);
			appCr.createAlias("regMember", "member");
			appCr.add(Restrictions.eq("storeBundleId", vo.getStoreBundleId()));
			appCr.add(
				Restrictions.or(
					Restrictions.and(
						Restrictions.eq("member.companyGb", "2"), 
						Restrictions.eq("member.userSeq", member.getUserSeq())
					),
					Restrictions.and(
						Restrictions.eq("member.companyGb", "1"), 
						Restrictions.eq("member.companySeq", member.getCompanySeq())
					)
				)
			);

			if(inAppList.getIsAvailable() != null && "false".equals(inAppList.getIsAvailable())) {
				appCr.add(
						Restrictions.or(
								Restrictions.eq("limitGb", "1"), 
								Restrictions.eq("useGb", "1"))
						);
			}
			
			if(inAppList.getSearchValue()!=null&&inAppList.getSearchValue().length()>0){
				if(inAppList.getSearchType()!=null&&inAppList.getSearchType().length()>0){
					appCr.add(Restrictions.and(Restrictions.like(inAppList.getSearchType(), "%"+inAppList.getSearchValue()+"%")));
				}else if((inAppList.getSearchType()==null||inAppList.getSearchType().length()==0)||"".equals(inAppList.getSearchType())){
					System.out.println("appList.getSearchType()====="+inAppList.getSearchType());
					System.out.println("appList.getSearchValue()====="+inAppList.getSearchValue());
					appCr.add(Restrictions.and(Restrictions.or(Restrictions.like("inappName", "%"+inAppList.getSearchValue()+"%"), Restrictions.like("descriptionText", "%"+inAppList.getSearchValue()+"%"))));
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
	public List<InApp> getListByBundleId(InApp vo, InAppList inAppList, Member member) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<InApp> list = null;

		try {
			tx = session.beginTransaction();
			System.out.println("Hello in Select List");
	
	
			Criteria appCr = session.createCriteria(InApp.class); //.createCriteria(AppVO.class);
			appCr.createAlias("regMember", "member");
			appCr.add(Restrictions.eq("storeBundleId", vo.getStoreBundleId()));
			appCr.add(Restrictions.or(Restrictions.and(Restrictions.eq("member.companyGb", "2"), Restrictions.eq("member.userSeq", member.getUserSeq())),Restrictions.and(Restrictions.eq("member.companyGb", "1"), Restrictions.eq("member.companySeq", member.getCompanySeq()))));

			if(inAppList.getIsAvailable() != null && "false".equals(inAppList.getIsAvailable())) {
				appCr.add(
						Restrictions.or(
								Restrictions.eq("limitGb", "1"), 
								Restrictions.eq("useGb", "1"))
						);
			}
			
			if(inAppList.getSearchValue()!=null&&inAppList.getSearchValue().length()>0){
				if(inAppList.getSearchType()!=null&&inAppList.getSearchType().length()>0){
					appCr.add(Restrictions.and(Restrictions.like(inAppList.getSearchType(), "%"+inAppList.getSearchValue()+"%")));
				}else if((inAppList.getSearchType()==null||inAppList.getSearchType().length()==0)||"".equals(inAppList.getSearchType())){
					appCr.add(Restrictions.and(Restrictions.or(Restrictions.like("inappName", "%"+inAppList.getSearchValue()+"%"), Restrictions.like("descriptionText", "%"+inAppList.getSearchValue()+"%"))));
				}
			}
	
			appCr.addOrder(Order.desc("chgDt"));
			list = appCr.list();
	
			for(InApp inApp : list) {
				Hibernate.initialize(inApp.getInAppSub());
			}
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
	public int getSeqAfterInsertAppInfo(InApp vo) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int inappSeq = 0;

		try {
			tx = session.beginTransaction();
			/*session.createCriteria(arg0)*/
			session.save(vo);
			inappSeq = vo.getInAppSeq();
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}

		return vo.getInAppSeq();
	}

	@Override
	public InApp selectForUpdate(InApp ivo, Member member) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		InApp vo = null;

		try {
			tx = session.beginTransaction();
	
			System.out.println("storeBUndle Id = " + ivo.getStoreBundleId());
			Criteria appCr = session.createCriteria(InApp.class, "inApp"); //.createCriteria(AppVO.class);
			appCr.createAlias("regMember", "member");
			appCr.add(Restrictions.eq("inApp.storeBundleId", ivo.getStoreBundleId()));
			appCr.add(Restrictions.eq("inApp.inappSeq", ivo.getInAppSeq()));
	
			appCr.add(Restrictions.or(Restrictions.and(Restrictions.eq("member.companyGb", "2"), Restrictions.eq("member.userSeq", member.getUserSeq())),Restrictions.and(Restrictions.eq("member.companyGb", "1"), Restrictions.eq("member.companySeq", member.getCompanySeq()))));
			
			vo = (InApp) appCr.uniqueResult();
			
			Hibernate.initialize(vo.getInAppSub());
		
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
		return vo;
	}


	@Override
	public void updateInAppMetaInfo(InAppMeta updated, int inappMetaSeq) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
	
			InAppMeta inAppMeta = session.get(InAppMeta.class, inappMetaSeq);

			if(updated.getInAppMetaAuthor() != null && !"".equals(updated.getInAppMetaAuthor()))
				inAppMeta.setInAppMetaAuthor(updated.getInAppMetaAuthor());
			
			if(updated.getInAppMetaBody() != null && !"".equals(updated.getInAppMetaBody()))
				inAppMeta.setInAppMetaBody(updated.getInAppMetaBody());
			
			if(updated.getInAppMetaBuildflag() != null && !"".equals(updated.getInAppMetaBuildflag()))
				inAppMeta.setInAppMetaBuildflag(updated.getInAppMetaBuildflag());
			
			if(updated.getInAppMetaCover1() != null && !"".equals(updated.getInAppMetaCover1()))
				inAppMeta.setInAppMetaCover1(updated.getInAppMetaCover1());
			
			if(updated.getInAppMetaCover2() != null && !"".equals(updated.getInAppMetaCover2()))
				inAppMeta.setInAppMetaCover2(updated.getInAppMetaCover2());
			
			if(updated.getInAppMetaCover3() != null && !"".equals(updated.getInAppMetaCover3()))
				inAppMeta.setInAppMetaCover3(updated.getInAppMetaCover3());
			
			if(updated.getInAppMetaCover4() != null && !"".equals(updated.getInAppMetaCover4()))
				inAppMeta.setInAppMetaCover4(updated.getInAppMetaCover4());
			
			if(updated.getInAppMetaDescription() != null && !"".equals(updated.getInAppMetaDescription()))
				inAppMeta.setInAppMetaDescription(updated.getInAppMetaDescription());
			
			if(updated.getInAppMetaDistributor() != null && !"".equals(updated.getInAppMetaDistributor()))
				inAppMeta.setInAppMetaDistributor(updated.getInAppMetaDistributor());
			
			if(updated.getInAppMetaISBN() != null && !"".equals(updated.getInAppMetaISBN()))
				inAppMeta.setInAppMetaISBN(updated.getInAppMetaISBN());
	
			if(updated.getInAppMetaPage() != null && !"".equals(updated.getInAppMetaPage()))
				inAppMeta.setInAppMetaPage(updated.getInAppMetaPage());
			
			if(updated.getInAppMetaPrice() != null && !"".equals(updated.getInAppMetaPrice()))
				inAppMeta.setInAppMetaPrice(updated.getInAppMetaPrice());
			
			if(updated.getInAppMetaSize() != null && !"".equals(updated.getInAppMetaSize()))
				inAppMeta.setInAppMetaSize(updated.getInAppMetaSize());
			
			if(updated.getInAppMetaStatus() != null && !"".equals(updated.getInAppMetaStatus()))
				inAppMeta.setInAppMetaStatus(updated.getInAppMetaStatus());
			
			if(updated.getInAppMetaSubtitle() != null && !"".equals(updated.getInAppMetaSubtitle()))
				inAppMeta.setInAppMetaSubtitle(updated.getInAppMetaSubtitle());
			
			if(updated.getInAppMetaTranslator() != null && !"".equals(updated.getInAppMetaTranslator()))
				inAppMeta.setInAppMetaTranslator(updated.getInAppMetaTranslator());
	
			if(updated.getInAppSeq() != null)
				inAppMeta.setInAppSeq(updated.getInAppSeq() );
				
			session.update(inAppMeta);
	
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
	}
	
	@Override
	public void updateInAppInfo(InApp updated, int inappSeq) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
	
			InApp inApp = session.get(InApp.class, inappSeq);
	
			if(updated.getInAppName() != null && !"".equals(updated.getInAppName() ))
			{inApp.setInAppName(updated.getInAppName());}
			if(updated.getCategorySeq() != null && !"".equals(updated.getCategorySeq()))
			{inApp.setCategorySeq(updated.getCategorySeq());}
			if(updated.getCategoryName() != null && !"".equals(updated.getCategoryName()))
			{inApp.setCategoryName(updated.getCategoryName());}
			if(updated.getChgUserGb() != null && !"".equals(updated.getChgUserGb() ))
			{inApp.setChgUserGb(updated.getChgUserGb());}
			if(updated.getChgUserId() != null && !"".equals(updated.getChgUserId() ))
			{inApp.setChgUserId(updated.getChgUserId());}
			if(updated.getChgUserSeq() != null && !"".equals(updated.getChgUserSeq() ))
			{inApp.setChgUserSeq(updated.getChgUserSeq());}
			if(updated.getCompletGb() != null && !"".equals(updated.getCompletGb() ))
			{inApp.setCompletGb(updated.getCompletGb());}
			if(updated.getCouponGb() != null && !"".equals(updated.getCouponGb() ))
			{inApp.setCouponGb(updated.getCouponGb());}
			// ���� �ѹ��� "" üũ�� ���ϴ������� ""�϶��� ""�� �ٲ��� �Ǳ⶧��
			if(updated.getCouponNum() != null)
			{inApp.setCouponNum(updated.getCouponNum());}
			if(updated.getDescriptionText() != null && !"".equals(updated.getDescriptionText() ))
			{inApp.setDescriptionText(updated.getDescriptionText());}
			if(updated.getDistrGb() != null && !"".equals(updated.getDistrGb() ))
			{inApp.setDistrGb(updated.getDistrGb());}
			if(updated.getInAppOrgFile() != null && !"".equals(updated.getInAppOrgFile() ))
			{inApp.setInAppOrgFile(updated.getInAppOrgFile());}
			if(updated.getInAppSaveFile() != null && !"".equals(updated.getInAppSaveFile() ))
			{inApp.setInAppSaveFile(updated.getInAppSaveFile());}
			if(updated.getIconOrgFile() != null && !"".equals(updated.getIconOrgFile() ))
			{inApp.setIconOrgFile(updated.getIconOrgFile());}
			if(updated.getIconSaveFile() != null && !"".equals(updated.getIconSaveFile() ))
			{inApp.setIconSaveFile(updated.getIconSaveFile());}
			if(updated.getLimitDt() != null )
			{inApp.setLimitDt(updated.getLimitDt());}
			if(updated.getLimitGb() != null && !"".equals(updated.getLimitGb() ))
			{inApp.setLimitGb(updated.getLimitGb());}
			if(updated.getMemDownAmt() != null && !"".equals(updated.getMemDownAmt() ))
			{inApp.setMemDownAmt(updated.getMemDownAmt());}
			if(updated.getMemDownCnt() != null && !"".equals(updated.getMemDownCnt() ))
			{inApp.setMemDownCnt(updated.getMemDownCnt());}
			if(updated.getMemDownEndDt() != null)
			{inApp.setMemDownEndDt(updated.getMemDownEndDt());}
			if(updated.getMemDownGb() != null && !"".equals(updated.getMemDownGb() ))
			{inApp.setMemDownGb(updated.getMemDownGb());}
			if(updated.getMemDownStartDt() != null)
			{inApp.setMemDownStartDt(updated.getMemDownStartDt());}
			if(updated.getNonmemDownAmt() != null && !"".equals(updated.getNonmemDownAmt() ))
			{inApp.setNonmemDownAmt(updated.getNonmemDownAmt());}
			if(updated.getNonmemDownCnt() != null && !"".equals(updated.getNonmemDownCnt() ))
			{inApp.setNonmemDownCnt(updated.getNonmemDownCnt());}
			if(updated.getNonmemDownEndDt() != null )
			{inApp.setNonmemDownEndDt(updated.getNonmemDownEndDt());}
			if(updated.getNonmemDownGb() != null && !"".equals(updated.getNonmemDownGb() ))
			{inApp.setNonmemDownGb(updated.getNonmemDownGb());}
			if(updated.getNonmemDownStarDt() != null )
			{inApp.setNonmemDownStarDt(updated.getNonmemDownStarDt());}
	
			if(updated.getRegDt() != null)
			{inApp.setRegDt(updated.getRegDt());}
	
			if(updated.getRegUserGb() != null && !"".equals(updated.getRegUserGb() ))
			{inApp.setRegUserGb(updated.getRegUserGb());}
			if(updated.getRegUserId() != null && !"".equals(updated.getRegUserId() ))
			{inApp.setRegUserId(updated.getRegUserId());}
			if(updated.getRegUserSeq() != null && !"".equals(updated.getRegUserSeq() ))
			{inApp.setRegUserSeq(updated.getRegUserSeq());}
	
			if(updated.getUseAvailDt() != null )
			{inApp.setUseAvailDt(updated.getUseAvailDt());}
			if(updated.getUseDisableDt() != null )
			{inApp.setUseDisableDt(updated.getUseDisableDt());}
	
			if(updated.getUseGb() != null && !"".equals(updated.getUseGb() ))
			{inApp.setUseGb(updated.getUseGb());}
			if(updated.getVerNum() != null && !"".equals(updated.getVerNum() ))
			{inApp.setVerNum(updated.getVerNum());}
			if(updated.getUseUserGb() != null && !"".equals(updated.getUseUserGb() ))
				inApp.setUseUserGb(updated.getUseUserGb());
			if(updated.getScreenType() != null && !"".equals(updated.getScreenType() ))
				inApp.setScreenType(updated.getScreenType());

			//20180403 : lsy - date update
			if(updated.getChgDt() != null) {
				inApp.setChgDt(updated.getChgDt());
			}
			if(updated.getDistributeReqDt() != null) {
				inApp.setDistributeReqDt(updated.getDistributeReqDt());
			}
			if(updated.getChgContentsDt() != null) {
				inApp.setChgContentsDt(updated.getChgContentsDt());
			}
			if(updated.getDistributeCompletDt() != null) {
				inApp.setDistributeCompletDt(updated.getDistributeCompletDt());
			}
			if(updated.getDistributeAcceptId() != null) {
				inApp.setDistributeAcceptId(updated.getDistributeAcceptId());
			}
			if(updated.getDistributeRequestId() != null) {
				inApp.setDistributeRequestId(updated.getDistributeRequestId());
			}

			if(updated.getInAppSize() != null) {
				inApp.setInAppSize(updated.getInAppSize());
			}
	
			session.update(inApp);
	
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
	}
	
	@Override
	public Object[] getListInAppForRelatedApp(String storeBundleId ) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Object[] result = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(InApp.class);
	
			System.out.println("storeBundleId = " + storeBundleId ) ;
			cr.add(Restrictions.eq("storeBundleId", storeBundleId ));
	
			List<InApp> list = cr.list();
			for(InApp inApp : list) {
				Hibernate.initialize(inApp.getInAppSub());
			}
	
			result = list.toArray();
	
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
	public List<InAppSub> selectInAppSubList(int inAppSeq) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List list = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(InAppSub.class);
			
			cr.add(
				Restrictions.eq("inappSeq", inAppSeq)
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
	public int insertInAppSubInfo(InAppSub inAppSub) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			session.save(inAppSub);
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}

		return inAppSub.getInAppSubSeq();
	}

	@Override
	public void deleteInAppSubInfo(InAppSub inAppSub) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
	
			String hql = "DELETE FROM InAppSub T " + 
		             "WHERE T.inappSeq = :inappSeq ";
			
			Query query = session.createQuery(hql);
			query.setParameter("inappSeq", inAppSub.getInAppSeq());
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
	public boolean checkInAppNameIfExist(String InAppName, String storeBundleId, String verNum) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<InApp> list = null;

		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(InApp.class);
			
			cr.add(
				Restrictions.and(
					Restrictions.eq("storeBundleId", storeBundleId),
					Restrictions.eq("inappName", InAppName),
					Restrictions.eq("verNum", verNum)
				)
			);
			list = cr.list();
	
			for(InApp inApp : list) {
				Hibernate.initialize(inApp.getInAppSub());
			}
			
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}

		if(list.size() == 0) {
			return true ;
		}else {
			return false;
		}
	}

	@Override
	public List<InApp> getListInAppIsAvailableByStoreBundleId(String storeBundleId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<InApp> list = null;
		
		try {
			tx = session.beginTransaction();
	
			Criteria cr = session.createCriteria(InApp.class);
			
			cr.add(
				Restrictions.and(
					Restrictions.eq("storeBundleId", storeBundleId),
					Restrictions.eq("limitGb","2"),
					Restrictions.eq("completGb", "1"),
					Restrictions.eq("useGb", "1")
				)
			);
	
			list = cr.list();
	
			for(InApp inApp : list) {
				Hibernate.initialize(inApp.getInAppSub());
			}
	
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
	public int insertInAppMetaInfo(InAppMeta inAppMeta) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			session.save(inAppMeta);
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}

		return inAppMeta.getInAppMetaSeq();
	}

	@Override
	public InAppMeta findByCustomInfoForMeta(String DBName, int intValue) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		InAppMeta inAppMeta = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(InAppMeta.class);
			Criterion user = Restrictions.eq(DBName, intValue);
			
			cr.add(user);
			inAppMeta = (InAppMeta) cr.uniqueResult();
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}

		return inAppMeta;

	}

	@Override
	public InAppMeta findByCustomInfoForMeta(String DBName, String value) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		InAppMeta inAppMeta = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(InAppMeta.class);
			Criterion user = Restrictions.eq(DBName, value);
			
			cr.add(user);
			inAppMeta = (InAppMeta) cr.uniqueResult();
			
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}

		return inAppMeta;
	}


	@Override
	public void deleteInAppInfo(String storeBundleId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		InAppMeta inAppMeta = null;
		
		try {
			tx = session.beginTransaction();
			
			String hql = "DELETE FROM InApp T " + 
		             "WHERE T.storeBundleId = :storeBundleId ";
			
			Query query = session.createQuery(hql);
			query.setParameter("storeBundleId", storeBundleId);
			query.executeUpdate();
			
			tx.commit();
		}catch (Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();	
		}finally {
			session.close();
		}
	}

	@Autowired
	private SqlSession sqlSession;

	@Override
	public String selectCompletGbBySeq(String DBName, int inapp_seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, inapp_seq);
	}
	
	@Override
	public String getSameInAppSeq (String DBName, String inapp_name, String store_bundle_id ) {
		// TODO Auto-generated method stub
		ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("inapp_name", inapp_name);
		paramMap.put("store_bundle_id", store_bundle_id);
		list.add(paramMap);
		
		return sqlSession.selectOne(DBName, list);
	}

	@Override
	public void insertInAppHistory(String DBName, String inAppSeq){
		sqlSession.insert(DBName, inAppSeq);
	}

	@Override
	public void deleteInAppBySeq(String DBName, String inAppSeq ){
		sqlSession.delete(DBName, inAppSeq);
	}

  	@Override
	public List<InApp> selectForHistory(String DBName, String inAppName, String storeBundleId) {
		// TODO Auto-generated method stub
		ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("inApp_name", inAppName);
		paramMap.put("store_bundle_id", storeBundleId);
		list.add(paramMap);
		
		return sqlSession.selectList(DBName, list);
	}

	@Override
	public InApp selectInAppBySeq(String DBName, int inAppSeq){
		return sqlSession.selectOne(DBName, inAppSeq);
	}

	@Override
	public int getInAppCntByBundleId(String DBName, String storeBundleId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, storeBundleId);
	}
	
	@Override
	public List<InApp> getInAppByBundleId(String DBName, String storeBundleId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(DBName, storeBundleId);
	}

}