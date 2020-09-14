package com.clbee.appmaker.service;

import com.clbee.appmaker.model.Member;
import com.clbee.appmaker.model.InApp;
import com.clbee.appmaker.model.InAppSub;
import com.clbee.appmaker.model.InAppMeta;
import com.clbee.appmaker.model.list.InAppList;

import java.util.List;

public interface InAppService {
	InApp findByCustomInfo( String DBName, int intValue);
	InApp findByCustomInfo( String DBName, String Value);
	List<InApp> getListInApp(String DBName, String storeBundleId, int userSeq);
	List<InApp> getListInApp(String DBName, String value);
	InAppList getListByBundleId(InApp vo, InAppList inAppList, Member member);
	int getSeqAfterInsertInAppInfo(InApp vo );
	InApp selectForUpdate(InApp ivo, Member member);
	void updateInAppInfo(InApp ivo, int inAppSeq);
	Object[] getListInAppForRelatedApp( String appSeq );
	List findListByCustomInfo(String DBName, String value);
	List findListByCustomInfo(String DBName, int value);
	List<InAppSub> selectInAppSubList ( int inAppSeq );
	int insertInAppSubInfo (InAppSub inAppSub );
	void deleteInAppSubInfo ( InAppSub inAppSub );
	void deleteInAppInfo( String storeBundleId );
	boolean checkInAppNameIfExist(String InAppName, String storeBundleId, String verNum);
	List<InApp> getListInAppIsAvailableByStoreBundleId ( String storeBundleId);
	int insertInAppMetaInfo ( InAppMeta inappMeta );
	InAppMeta findByCustomInfoForMeta( String DBName, int intValue);
	InAppMeta findByCustomInfoForMeta( String DBName, String value);
	void updateInAppMetaInfo(InAppMeta updated, int inAppMetaSeq);
	
	//20180327 - lsy : develop version managemenet
	String selectCompletGbBySeq(int inAppSeq);
	String getSameInAppSeq ( String inAppName, String storeBundleId );
	void insertInAppHistory( String inAppSeq );
	void deleteInAppBySeq( String inAppSeq );
	
	List<InApp> selectForHistory( String inAppName, String storeBundleId );
	int infoUpdateCheck( InApp inAppForm, int inAppSeq);
	//20180327 - lsy : develop version managemenet - end
	
	//20180619 - lsy : when app request(Library), load inapp info
	InAppList getInAppByBundleId(InAppList inAppList, String storeBundleId);
}
