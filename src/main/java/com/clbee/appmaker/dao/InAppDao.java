package com.clbee.appmaker.dao;

import com.clbee.appmaker.model.InApp;
import com.clbee.appmaker.model.list.InAppList;
import com.clbee.appmaker.model.InAppSub;
import com.clbee.appmaker.model.InAppMeta;
import com.clbee.appmaker.model.Member;

import java.util.List;

public interface InAppDao {
	InApp findByCustomInfo(String DBName, int intValue);
	InApp findByCustomInfo(String DBName, String Value);
	List<InApp> getListInApp(String DBName, String storeBundleId, int userSeq);
	List<InApp> getListInApp(String DBName, String value);
	int getListCntByBundleId(InApp vo, InAppList inAppList, Member member);
	List<InApp> getListByBundleId(InApp vo, InAppList inAppList, Member member);
	int getSeqAfterInsertAppInfo(InApp vo);
	InApp selectForUpdate(InApp ivo, Member member);
	void updateInAppInfo(InApp ivo, int inappSeq);
	Object[] getListInAppForRelatedApp(String appSeq);
	List findListByCustomInfo(String DBName, String value);
	List findListByCustomInfo(String DBName, int value);
	List<InAppSub> selectInAppSubList(int inAppSeq);
	int insertInAppSubInfo(InAppSub inAppSub);
	void deleteInAppSubInfo(InAppSub inAppSub);
	void deleteInAppInfo(String storeBundleId);
	boolean checkInAppNameIfExist(String InAppName, String storeBundleId, String verNum);
	List<InApp> getListInAppIsAvailableByStoreBundleId(String storeBundleId);
	int insertInAppMetaInfo(InAppMeta inappMeta);
	InAppMeta findByCustomInfoForMeta(String DBName, int intValue);
	InAppMeta findByCustomInfoForMeta(String DBName, String value);
	void updateInAppMetaInfo(InAppMeta updated, int inAppMetaSeq);

	String selectCompletGbBySeq(String DBName, int inAppSeq);
	String getSameInAppSeq(String DBName, String inAppName, String storeBundleId);
	void insertInAppHistory(String DBName, String inAppSeq);
	void deleteInAppBySeq(String DBName, String inAppSeq);

	List<InApp> selectForHistory(String DBName, String inAppName, String storeBundleId);

	InApp selectInAppBySeq(String DBName, int inAppSeq);

	int getInAppCntByBundleId(String DBName, String store_bundle_id);
	List<InApp> getInAppByBundleId(String DBName, String store_bundle_id);
}