package com.clbee.appmaker.dao;

import com.clbee.appmaker.model.AppHistory;
import com.clbee.appmaker.model.AppSub;
import com.clbee.appmaker.model.list.AppList;
import com.clbee.appmaker.model.list.DownloadList;
import com.clbee.appmaker.model.Member;
import com.clbee.appmaker.util.Entity;
import com.clbee.appmaker.model.App;

import java.util.List;

public interface AppDao {
	int insertAppInfo(App app );
	int insertAppHistoryInfo(AppHistory app);
	void updateAppInfo(App updated, int appNum) throws Exception;
	void updateAppSubInfo(AppSub updated, int subNum);
	List<App> selectList( int startNo, String user_id );
	int getListCount(String user_id);
	App selectById(int appNum );
	App selectByStoreId( String storeBundleId );
	Object selectByBundleId( Entity param );
	List<App> selectByUserId(String user_id);
	void updateAppByIdentifier ( App updated, String store_bundle_id );
	List<App> selectByUserIdAndIdenty( String user_id, String bundle_identy);
	int getSeqAfterInsertAppInfo(App app);
	int getListCount(AppList appList, Member member);
	List<App> selectList(AppList appList, Member member);
	App selectForUpdate(App app, Member member);
	int getListCount(DownloadList downloadList, Member member);
	List<?> selectList(DownloadList downloadList, Member member);
	List selectList(Entity param);
	List getSelectListCount(Entity param);
	List getSelectCouponList(Entity param);
	String getSelectTodayDate();
	List getListNotComplte(Member member);
	List getCountOfIdenticalCouponNumForAll( Entity param );
	List getRowIsCompletedByBundleId( Entity param);
	void deleteAppInfo( int appSeq );
	void deleteAppHistoryInfo( int appSeq );
	void deleteAppSubAppSeqInfo(int appSeq);
	List<AppSub> selectAppSubList ( int appSeq );
	int insertAppSubInfo(AppSub appSub );
	void deleteAppSubInfo ( AppSub appSub );
	List<App> getNotPermmitList(int companySeq, Integer[] useA, String searchValue, String searchType);
	List<App> getPermitList(int companySeq, Integer[] useA);
	int checkIfAvailableAppByBundleId ( int userSeq, String ostype, String storeBundleId);
	List<App> getListIsAvailableByCompanySeq( int companySeq );
	App selectByBundleIdAndOstype ( String ostype, String storeBundleId );
	
	List<App> selectAppForHistory( String DBName, String store_bundle_id );
	App selectAppBySeq( String DBName, int appSeq );
	
	int duplicateVerCheck(String DBName, Entity param);
	
	App selectBeforAppInfoBySeqForNewVersion ( String DBName, int app_seq );
	int insertNewVersionAppInfo(String DBName, App newVersionAppInfo);
	App selectAppByAppSeqForMakeJobTicket(String DBName, int app_seq);
	
	String selectAppVersionMax(String DBName, int app_seq);
}
