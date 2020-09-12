package com.clbee.appmaker.service;

import com.clbee.appmaker.model.App;
import com.clbee.appmaker.model.AppHistory;
import com.clbee.appmaker.model.AppSub;
import com.clbee.appmaker.model.list.DownloadList;
import com.clbee.appmaker.model.Member;
import com.clbee.appmaker.util.Entity;
import com.clbee.appmaker.model.list.AppList;

import java.util.HashMap;
import java.util.List;

public interface AppService {
	int insertAppInfo( App app);
	int insertAppHistoryInfo( AppHistory app);
	void updateAppInfo(App updated, int appNum) throws Exception;
	void updateAppSubInfo (AppSub updated, int subNum);
	App selectById(int appNum);
	App selectByStoreId(String storeBundleId);
	AppList selectList(int currentPage, String user_id);
	int getListCount(String user_id);
	List<App> selectByUserId(String user_id);
	void updateAppByIdentifier(App updatedVO, String store_bundle_id);
	List<App> selectByUserIdAndIdenty(String user_id, String bundle_identy);
	Object selectByBundleId( Entity param);
	int getSeqAfterInsertAppInfo(App app);
	AppList selectList(Member Member, AppList appList);
	App selectForUpdate(App App, Member Member);
	DownloadList selectList(Member Member, DownloadList downloadList);
	List<App> selectAppListForRelatedApp(Member Member);
	List getSelectList(Entity param);
	List getSelectListCount(Entity param);
	List getSelectCouponList(Entity param);
	String getSelectTodayDate();
	List getListNotComplte(Member Member);
	List getCountOfIdenticalCouponNumForAll( Entity param);
	List getRowIsCompletedByBundleId( Entity param);
	void deleteAppInfo( int appSeq);
	void deleteAppHistoryInfo( int appSeq);
	void deleteAppSubAppSeqInfo(int appSeq);
	List<AppSub> selectAppSubList(int appSeq);
	int insertAppSubInfo(AppSub appSub);
	void deleteAppSubInfo(AppSub appSub);
	List<App> getNoPermitList(int companySeq, Integer[] useA, String searchValue, String searchType);
	List<App> getPermitList(int companySeq, Integer[] useA);
	int checkIfAvailableAppByBundleId(int userSeq, String ostype, String storeBundleId);
	List<App >getListIsAvailableByCompanySeq( int companySeq);
	HashMap<Object, Object>  selectByBundleIdAndOstype(String ostype, String storeBundleId);
	
	List<App> selectAppForHistory( String store_bundle_id);
	int infoUpdateCheck( App appFormVO, int app_seq);

	int duplicateVerCheck(Entity param);
	
	App selectBeforAppInfoBySeqForNewVersion(int app_seq);
	int insertNewVersionAppInfo(App newVersionAppInfo);
	App selectAppByAppSeqForMakeJobTicket(int app_seq);
	
	String selectAppVersionMax(int app_seq);
}