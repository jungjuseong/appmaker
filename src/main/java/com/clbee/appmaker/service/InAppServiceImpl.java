package com.clbee.appmaker.service;

import com.clbee.appmaker.model.InApp;
import com.clbee.appmaker.model.InAppMeta;
import com.clbee.appmaker.model.InAppSub;
import com.clbee.appmaker.model.Member;
import com.clbee.appmaker.model.list.InAppList;
import com.clbee.appmaker.dao.InAppDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InAppServiceImpl implements InAppService {

	@Autowired
	private InAppDao inAppDao;
	
	@Override
	public InApp findByCustomInfo(String DBName, int intValue) {
		// TODO Auto-generated method stub
		return inAppDao.findByCustomInfo(DBName, intValue);
	}

	@Override
	public InApp findByCustomInfo(String DBName, String Value) {
		// TODO Auto-generated method stub
		return inAppDao.findByCustomInfo(DBName, Value);
	}

	@Override
	public List<InApp> getListInApp(String DBName, String storeBundleId, int userSeq) {
		// TODO Auto-generated method stub
		return inAppDao.getListInApp(DBName, storeBundleId, userSeq);
	}

	@Override
	public List<InApp> getListInApp(String DBName, String value) {
		// TODO Auto-generated method stub
		return inAppDao.getListInApp(DBName, value);
	}

	@Override
	public InAppList getListByBundleId(InApp vo, InAppList inAppList, Member memberVO) {
		// TODO Auto-generated method stub
		//AppList list = null;
		int pageSize = 10;
		int maxResult = 10;
		int totalCount = 0;
		
		try{
			totalCount = inAppDao.getListCntByBundleId(vo, inAppList, memberVO);
			System.out.println("totalCount = " + totalCount);
			
			inAppList.calc(pageSize, totalCount, inAppList.getCurrentPage(), maxResult);		

			List<InApp> list = inAppDao.getListByBundleId(vo, inAppList, memberVO);
			
			inAppList.setList(list);
			
			System.out.println("[ListService] - selectList method");
			System.out.println("selectList[] " + list.size());
			System.out.println(list.size());
			
		}catch(Exception e){
			System.out.println("����");
			e.printStackTrace();
		}
		return inAppList;
	}

	@Override
	public int getSeqAfterInsertInAppInfo(InApp vo) {
		// TODO Auto-generated method stub
		return inAppDao.getSeqAfterInsertAppInfo(vo);
	}

	@Override
	public InApp selectForUpdate(InApp ivo, Member memberVO) {
		// TODO Auto-generated method stub
		return inAppDao.selectForUpdate(ivo, memberVO);
	}

	@Override
	public void updateInAppInfo(InApp ivo, int inAppSeq) {
		// TODO Auto-generated method stub
		inAppDao.updateInAppInfo(ivo, inAppSeq);
	}

	@Override
	public Object[] getListInAppForRelatedApp(String appSeq) {
		// TODO Auto-generated method stub
		return inAppDao.getListInAppForRelatedApp( appSeq );
	}

	@Override
	public List findListByCustomInfo(String DBName, String value) {
		// TODO Auto-generated method stub
		return inAppDao.findListByCustomInfo(DBName, value);
	}

	@Override
	public List findListByCustomInfo(String DBName, int value) {
		// TODO Auto-generated method stub
		return inAppDao.findListByCustomInfo(DBName, value);
	}

	@Override
	public List<InAppSub> selectInAppSubList(int inAppSeq) {
		// TODO Auto-generated method stub
		return inAppDao.selectInAppSubList(inAppSeq);
	}

	@Override
	public int insertInAppSubInfo(InAppSub inAppSubVO) {
		// TODO Auto-generated method stub
		return inAppDao.insertInAppSubInfo(inAppSubVO);
	}

	@Override
	public void deleteInAppSubInfo(InAppSub inAppSubVO) {
		// TODO Auto-generated method stub
		inAppDao.deleteInAppSubInfo(inAppSubVO);
	}

	@Override
	public boolean checkInAppNameIfExist(String inAppName, String storeBundleId, String verNum) {
		// TODO Auto-generated method stub
		return inAppDao.checkInAppNameIfExist(inAppName, storeBundleId, verNum);
	}

	@Override
	public List<InApp> getListInAppIsAvailableByStoreBundleId (String storeBundleId) {
		// TODO Auto-generated method stub
		return inAppDao.getListInAppIsAvailableByStoreBundleId(storeBundleId);
	}

	@Override
	public int insertInAppMetaInfo(InAppMeta inAppMeta) {
		// TODO Auto-generated method stub
		return inAppDao.insertInAppMetaInfo(inAppMeta);
	}

	@Override
	public InAppMeta findByCustomInfoForMeta(String DBName, int intValue) {
		// TODO Auto-generated method stub
		return inAppDao.findByCustomInfoForMeta(DBName, intValue);
	}

	@Override
	public InAppMeta findByCustomInfoForMeta(String DBName, String value) {
		// TODO Auto-generated method stub
		return inAppDao.findByCustomInfoForMeta(DBName, value);
	}

	@Override
	public void updateInAppMetaInfo(InAppMeta updated, int inAppMetaSeq) {
		// TODO Auto-generated method stub
		inAppDao.updateInAppMetaInfo(updated, inAppMetaSeq);
	}

	@Override
	public void deleteInAppInfo( String storeBundleId ) {
		// TODO Auto-generated method stub
		inAppDao.deleteInAppInfo(storeBundleId);
	}

	@Override
	public String selectCompletGbBySeq( int inAppSeq ) {
		// TODO Auto-generated method stub
		return inAppDao.selectCompletGbBySeq("selectCompletGbBySeq", inAppSeq);
	}

	@Override
	public String getSameInAppSeq ( String inAppName, String storeBundleId ) {
		// TODO Auto-generated method stub
		return inAppDao.getSameInAppSeq("getSameInAppSeq", inAppName, storeBundleId);
	}

	@Override
	public void insertInAppHistory( String inAppSeq ){
		inAppDao.insertInAppHistory("insertInAppHistory", inAppSeq);
	}

	@Override
	public void deleteInAppBySeq( String inAppSeq ){
		inAppDao.deleteInAppBySeq("deleteInAppBySeq", inAppSeq);
	}
	
	@Override
	public List<InApp> selectForHistory( String inAppName, String storeBundleId ) {
		return inAppDao.selectForHistory( "selectInAppForHistory", inAppName, storeBundleId );
	}
	
	@Override
	public int infoUpdateCheck(InApp inAppForm, int inAppSeq) {
		// TODO Auto-generated method stub
		int infoUpdate = 0;
		InApp inApp = inAppDao.selectInAppBySeq( "selectInAppBySeq", inAppSeq );
		
		if(inApp.getCompletGb().equals("2") || inApp.getCompletGb().equals("4")) {
			if( inAppForm.getInAppName() != null && !"".equals(inAppForm.getInAppName()) && !(inAppForm.getInAppName().equals(inApp.getInAppName())) ) {	infoUpdate += 1;	}
			if( inAppForm.getVerNum() != null && !"".equals(inAppForm.getVerNum()) && !(inAppForm.getVerNum().equals(inApp.getVerNum())) ) {	infoUpdate += 1;	}
			if( inAppForm.getCategorySeq() != null && !"".equals(inAppForm.getCategorySeq()) && !(inAppForm.getCategorySeq().equals(inApp.getCategorySeq())) ) {	infoUpdate += 1;	}
			if( inAppForm.getCategoryName() != null && !"".equals(inAppForm.getCategoryName()) && !(inAppForm.getCategoryName().equals(inApp.getCategoryName())) ) {	infoUpdate += 1;	}
			if( inAppForm.getDescriptionText() != null && !"".equals(inAppForm.getDescriptionText()) && !(inAppForm.getDescriptionText().equals(inApp.getDescriptionText())) ) {	infoUpdate += 1;	}
			
			if(inAppForm.getIconOrgFile() != null && !"".equals(inAppForm.getIconOrgFile()) && !(inAppForm.getIconOrgFile().equals(inApp.getIconOrgFile())) ) {	infoUpdate += 1;	}
			if(inAppForm.getIconSaveFile() != null && !"".equals(inAppForm.getIconSaveFile()) && !(inAppForm.getIconSaveFile().equals(inApp.getIconSaveFile())) ) {	infoUpdate += 1;	}
			
		}
		
		if( inAppForm.getUseGb() != null && !"".equals(inAppForm.getUseGb()) && !(inAppForm.getUseGb().equals(inApp.getUseGb())) ) {	infoUpdate += 1;	}
		if( inAppForm.getUseUserGb() != null && !"".equals(inAppForm.getUseUserGb()) && !(inAppForm.getUseUserGb().equals(inApp.getUseUserGb())) ) {	infoUpdate += 1;	}
		if( inAppForm.getScreenType() != null && !"".equals(inAppForm.getScreenType()) && !(inAppForm.getScreenType().equals(inApp.getScreenType())) ) {	infoUpdate += 1;	}
		
		return infoUpdate;
	}

	@Override
	public InAppList getInAppByBundleId(InAppList inAppList, String store_bundle_id) {
		// TODO Auto-generated method stub
		int totalCount = 0;
		
		try{
			totalCount = inAppDao.getInAppCntByBundleId("getInAppCntByBundleId", store_bundle_id);
			inAppList.setTotalCount(totalCount);
			
			List<InApp> list = inAppDao.getInAppByBundleId("getInAppByBundleId", store_bundle_id);			
			inAppList.setList(list);			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return inAppList;
	}
}