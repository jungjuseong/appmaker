package com.clbee.appmaker.service;

import com.clbee.appmaker.dao.AppDao;
import com.clbee.appmaker.model.AppHistory;
import com.clbee.appmaker.model.AppSub;
import com.clbee.appmaker.model.list.AppList;
import com.clbee.appmaker.model.list.DownloadList;
import com.clbee.appmaker.model.Member;
import com.clbee.appmaker.util.Entity;
import com.clbee.appmaker.model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AppServiceImpl implements AppService {
	@Autowired 
	AppDao dao;
	
	@Autowired
	MessageSource messageSource;
	
	@Override
	public int insertAppInfo( App app ) {
		return dao.insertAppInfo( app );
	}
	@Override
	public void updateAppInfo(App updated, int appNum)throws Exception {
		dao.updateAppInfo(updated, appNum);
	}
	
	@Override
	public App selectByStoreId( String storeBundleId ) {
		return dao.selectByStoreId( storeBundleId );
	}

	@Override
	public App selectById( int appNum ) {
		return dao.selectById( appNum );
	}
	
	@Override
	public AppList selectList( int currentPage, String user_id ) {

		AppList list = null;

		int pageSize = 10;
		int maxResult = 10;
		int totalCount = 0;
		int startNo = 0;

		try{
			totalCount = dao.getListCount(user_id);
			System.out.println("totalCount = " + totalCount);
			
			list = new AppList(pageSize, totalCount, currentPage, maxResult);
		
			startNo = (currentPage-1) *10;

			List<App> vo = dao.selectList(startNo, user_id);
			
			list.setList(vo);
			
			System.out.println("[ListService] - selectList method");
			System.out.println("selectList[] " + vo.size());
			System.out.println(vo.size());
			
		}catch(Exception e){
			System.out.println("����");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getListCount(String user_id) {
		return dao.getListCount(user_id);
	}

	@Override
	public List<App> selectByUserId(String user_id) {
		// TODO Auto-generated method stub
		return dao.selectByUserId(user_id);
	}

	@Override
	public void updateAppByIdentifier(App updated, String store_bundle_id) {
		dao.updateAppByIdentifier(updated, store_bundle_id);
	}

	@Override
	public List<App> selectByUserIdAndIdenty(String user_id, String bundle_identy) {
		return dao.selectByUserIdAndIdenty(user_id, bundle_identy);
	}

	@Override
	public Object selectByBundleId(Entity param) {
		// TODO Auto-generated method stub
		return dao.selectByBundleId( param);
	}

	@Override
	public int getSeqAfterInsertAppInfo(App app) {
		// TODO Auto-generated method stub
		return dao.getSeqAfterInsertAppInfo(app);
	}

	@Override
	public AppList selectList(Member member, AppList appList) {
		// TODO Auto-generated method stub
		//AppList list = null;
		int pageSize = 10;
		int maxResult = 10;
		int totalCount = 0;
		
		try{
			totalCount = dao.getListCount(appList, member);
			System.out.println("totalCount = " + totalCount);
			
			appList.calc(pageSize, totalCount, appList.getCurrentPage(), maxResult);		

			List<App> vo = dao.selectList(appList, member);
			
			appList.setList(vo);
			
			System.out.println("[ListService] - selectList method");
			System.out.println("selectList[] " + vo.size());
			System.out.println(vo.size());
			
		}catch(Exception e){
			System.out.println("����");
			e.printStackTrace();
		}
		return appList;
	}

	@Override
	public App selectForUpdate(App App, Member member) {
		// TODO Auto-generated method stub
		return dao.selectForUpdate(App, member);
	}

	@Override
	public DownloadList selectList(Member member, DownloadList downloadList) {
		// TODO Auto-generated method stub
		//AppList list = null;
		int pageSize = 10;
		int maxResult = 10;
		int totalCount = 0;

		try{
			totalCount = dao.getListCount(downloadList, member);
			System.out.println("totalCount = " + totalCount);
			
			downloadList.calc(pageSize, totalCount, downloadList.getCurrentPage(), maxResult);		

			List<?> vo = dao.selectList(downloadList, member);
			
			downloadList.setList(vo);
			
			System.out.println("[ListService] - selectList method");
			System.out.println("selectList[] " + vo.size());
			System.out.println(vo.size());
			
		}catch(Exception e){
			System.out.println("����");
			e.printStackTrace();
		}			
			return downloadList;
	}

	@Override
	public List<App> selectAppListForRelatedApp(Member member){
		return dao.selectList((AppList)(null), member);
	}

	@Override
	public List getSelectList(Entity param) {
		return dao.selectList(param);
	}

	@Override
	public List getSelectListCount(Entity param) {
		return dao.getSelectListCount(param);
	}

	@Override
	public List getSelectCouponList(Entity param) {
		return dao.getSelectCouponList(param);		
	}

	@Override
	public String getSelectTodayDate() {
		return dao.getSelectTodayDate();		
	}

	@Override
	public List getListNotComplte(Member member) {
		// TODO Auto-generated method stub
		return dao.getListNotComplte(member);
	}

	@Override
	public List getCountOfIdenticalCouponNumForAll(Entity param) {
		return dao.getCountOfIdenticalCouponNumForAll(param);
	}

	@Override
	public List getRowIsCompletedByBundleId(Entity param) {
		return dao.getRowIsCompletedByBundleId(param);
	}

	@Override
	public void deleteAppInfo(int appSeq) {
		dao.deleteAppInfo(appSeq);
	}
	
	@Override
	public void deleteAppHistoryInfo(int appSeq) {
		dao.deleteAppHistoryInfo(appSeq);
	}

	@Override
	public int insertAppHistoryInfo(AppHistory app) {
		// TODO Auto-generated method stub
		return dao.insertAppHistoryInfo(app);
	}

	@Override
	public List<AppSub> selectAppSubList(int appSeq) {
		// TODO Auto-generated method stub
		return dao.selectAppSubList(appSeq);
	}

	@Override
	public int insertAppSubInfo(AppSub AppSub) {
		// TODO Auto-generated method stub
		return dao.insertAppSubInfo( AppSub );
	}

	@Override
	public void deleteAppSubInfo(AppSub AppSub) {
		// TODO Auto-generated method stub
		dao.deleteAppSubInfo(AppSub);
	}

	@Override
	public List<App> getNotPermmitList(int companySeq, Integer[] useA,
			String searchValue, String searchType) {
		// TODO Auto-generated method stub
		return dao.getNotPermmitList(companySeq, useA, searchValue, searchType);
	}

	@Override
	public List<App> getPermitList(int companySeq, Integer[] useA) {
		// TODO Auto-generated method stub
		return dao.getPermitList(companySeq, useA);
	}

	@Override
	public int checkIfAvailableAppByBundleId( int userSeq, String ostype,
			String storeBundleId) {
		// TODO Auto-generated method stub
		return dao.checkIfAvailableAppByBundleId( userSeq, ostype, storeBundleId );
	}

	@Override
	public List<App> getListIsAvailableByCompanySeq(int companySeq) {
		// TODO Auto-generated method stub
		List<App> tempList = new ArrayList<App>();
		List<String> tempStringList = new ArrayList<String>();
		List<App> AppList =  dao.getListIsAvailableByCompanySeq(companySeq);
		if(AppList != null){
			for(App item : AppList) {
				if(!tempStringList.contains(item.getStoreBundleId())) {
					tempList.add(item);
					tempStringList.add(item.getStoreBundleId());
				}
			}
		}
		return tempList;
	}

	@Override
	public HashMap<Object, Object> selectByBundleIdAndOstype(String ostype, String storeBundleId) {
		App app =  dao.selectByBundleIdAndOstype(ostype, storeBundleId);
		app.setAppSubList(null);
		app.setChgMember(null);
		app.setRegMember(null);
		HashMap<Object, Object> map = new HashMap<Object, Object>();

		/* 1. OK
		 * 2. ��ȿ ��¥�� ������.
		 * 3. �˼� ���� ����
		 * 4. ������ ostype�� bundleid�� ��ġ�ϴ� ���� �����ϴ�.
		 * 5. ��������� ���Դϴ�.
		 * 6. ���ѵ� ���Դϴ�.
		 * */
		int result = 5001;
		try {
			if(app == null) {
				result = 5004;
			}
			else {
				if("2".equals(app.getMemDownGb()) && app.getMemDownStartDt() != null && app.getMemDownEndDt() != null){
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
					DateFormat inputDF  = new SimpleDateFormat("MM/dd/yy");
					Date date = new Date();			
					Date formattedDate;
					formattedDate = inputDF.parse(format.format(date));
		
					Calendar cal = Calendar.getInstance();
					cal.setTime(formattedDate);
					
					int month = cal.get(Calendar.MONTH);
					int day = cal.get(Calendar.DAY_OF_MONTH);
					int year = cal.get(Calendar.YEAR);

					formattedDate = inputDF.parse(format.format(app.getMemDownStartDt()));
					
					cal.setTime(formattedDate);
					int stMonth = cal.get(Calendar.MONTH);
					int stDay = cal.get(Calendar.DAY_OF_MONTH);
					int stYear = cal.get(Calendar.YEAR);
					
					formattedDate = inputDF.parse(format.format(app.getMemDownEndDt()));
					
					cal.setTime(formattedDate);
					int endMonth = cal.get(Calendar.MONTH);
					int endDay = cal.get(Calendar.DAY_OF_MONTH);
					int endYear = cal.get(Calendar.YEAR);
				
		
					// 1. year(���� �⵵)�� stYear���� Ŭ��� month�� day�� �ƹ�����̾���
					// 2. year�� stYear�� ������� month�� ����
					// 3. �̶� month�� stMonth���� Ŭ��� day�� �ƹ������ ����
					// 4. month�� stMonth�� ������� stDay�� ����
					// endYear�� 1, 2, 3, 4 �� ����
					if(year > stYear || (year == stYear && (month > stMonth || (month == stMonth && day >= stDay)))){
						if(year < endYear || (year == endYear && (month < endMonth || (month == endMonth && day <= endDay)))){
							result = 5001;
						}
						else {
							result = 5002;
						}
					}else {
						result = 5002;
					}
				}
				if("2".equals(app.getUseGb())) result =  5005;
				if("1".equals(app.getLimitGb())) result =  5006;
					
			}
			return map;
		}catch(Exception e) {
			e.printStackTrace();
			result = 5003;
			return map;
		}finally{
			switch(result) {
				case 5001 :
					map.put("message", "");
					break;
				case 5002 :
					map.put("message", messageSource.getMessage("app.execute.5002", null, Locale.getDefault()));
					break;
				case 5003 :
					map.put("message", messageSource.getMessage("app.execute.5003", null, Locale.getDefault()));
					break;
				case 5004 :
					map.put("message", messageSource.getMessage("app.execute.5004", null, Locale.getDefault()));
					break;
				case 5005 :
					map.put("message", messageSource.getMessage("app.execute.5005", null, Locale.getDefault()));
					break;
				case 5006 :
					map.put("message", messageSource.getMessage("app.execute.5006", null, Locale.getDefault()));
					break;		
				}
			map.put("result", result);
			map.put("appInfo", app);
		}
	}

	@Override
	public void updateAppSubInfo(AppSub updated, int subNum) {
		dao.updateAppSubInfo(updated, subNum);
	}

	@Override
	public void deleteAppSubAppSeqInfo(int appSeq) {
		dao.deleteAppSubAppSeqInfo(appSeq);
	}
	
	//20180327 - lsy : develop version managemenet
	@Override
	public List<App> selectAppForHistory( String store_bundle_id ) {
		return dao.selectAppForHistory( "selectAppForHistory", store_bundle_id );
	}
	
	@Override
	public int infoUpdateCheck(App appFormVO, int app_seq) {
		// TODO Auto-generated method stub
		int infoUpdate = 0;
		App App = dao.selectAppBySeq( "selectAppBySeq", app_seq );
		
		if(App.getCompletGb().equals("2") || App.getCompletGb().equals("4")) {
			if(App.getRegGb().equals("1")) {
				if( appFormVO.getAppContentsAmt() != null && !"".equals(appFormVO.getAppContentsAmt()) && !(appFormVO.getAppContentsAmt().equals(App.getAppContentsAmt())) ) {	infoUpdate += 1;	}
				if( appFormVO.getAppContentsGb() != null && !"".equals(appFormVO.getAppContentsGb()) && !(appFormVO.getAppContentsGb().equals(App.getAppContentsGb())) ) {	infoUpdate += 1;	}
			}
			if( appFormVO.getAppName() != null && !"".equals(appFormVO.getAppName()) && !(appFormVO.getAppName().equals(App.getAppName())) ) {	infoUpdate += 1;	}
			if( appFormVO.getFileName() != null && !"".equals(appFormVO.getFileName()) && !(appFormVO.getFileName().equals(App.getFileName())) ) {	infoUpdate += 1;	}
			if( appFormVO.getVerNum() != null && !"".equals(appFormVO.getVerNum()) && !(appFormVO.getVerNum().equals(App.getVerNum())) ) {	infoUpdate += 1;	}
			if( appFormVO.getVersionCode() != null && !"".equals(appFormVO.getVersionCode()) && !(appFormVO.getVersionCode().equals(App.getVersionCode())) ) {	infoUpdate += 1;	}			
			if( appFormVO.getTemplateName() != null && !"".equals(appFormVO.getTemplateName()) && !(appFormVO.getTemplateName().equals(App.getTemplateName())) ) {	infoUpdate += 1;	}
			if( appFormVO.getTemplateSeq() != null && !"".equals(appFormVO.getTemplateSeq()) && !(appFormVO.getTemplateSeq().equals(App.getTemplateSeq())) ) {	infoUpdate += 1;	}
			if( appFormVO.getDescriptionText() != null && !"".equals(appFormVO.getDescriptionText()) && !(appFormVO.getDescriptionText().equals(App.getDescriptionText())) ) {	infoUpdate += 1;	}
			if( appFormVO.getChgText() != null && !"".equals(appFormVO.getChgText()) && !(appFormVO.getChgText().equals(App.getChgText())) ) {	infoUpdate += 1;	System.out.println("test====>"+infoUpdate+",,"+appFormVO.getChgText()+",,"+App.getChgText());}
			if( appFormVO.getIconOrgFile() != null && !"".equals(appFormVO.getIconOrgFile()) && !(appFormVO.getIconOrgFile().equals(App.getIconOrgFile())) ) {	infoUpdate += 1;	}
			if( appFormVO.getIconSaveFile() != null && !"".equals(appFormVO.getIconSaveFile()) && !(appFormVO.getIconSaveFile().equals(App.getIconSaveFile())) ) {	infoUpdate += 1;	}
		}
		if( appFormVO.getLoginGb() != null && !"".equals(appFormVO.getLoginGb()) && !(appFormVO.getLoginGb().equals(App.getLoginGb())) ) {	infoUpdate += 1;	}
		if( appFormVO.getUseGb() != null && !"".equals(appFormVO.getUseGb()) && !(appFormVO.getUseGb().equals(App.getUseGb())) ) {	infoUpdate += 1;	}
		if( appFormVO.getUseUserGb() != null && !"".equals(appFormVO.getUseUserGb()) && !(appFormVO.getUseUserGb().equals(App.getUseUserGb())) ) {	infoUpdate += 1;	}
		
		if( appFormVO.getInstallGb() != null && !"".equals(appFormVO.getInstallGb()) && !(appFormVO.getInstallGb().equals(App.getInstallGb())) ) {	infoUpdate += 1;	}
		
		if( appFormVO.getDistrGb() != null && !"".equals(appFormVO.getDistrGb()) && !(appFormVO.getDistrGb().equals(App.getDistrGb())) ) {	infoUpdate += 1;	}
		if( appFormVO.getMemDownGb() != null  && !"".equals(appFormVO.getMemDownGb()) && !(appFormVO.getMemDownGb().equals(App.getMemDownGb())) ) {	infoUpdate += 1;	}
		if( appFormVO.getMemDownAmt() != null && !"".equals(appFormVO.getMemDownAmt()) && !(appFormVO.getMemDownAmt().equals(App.getMemDownAmt())) ) {	infoUpdate += 1;	}
		if( appFormVO.getMemDownStartDt() != null && !(appFormVO.getMemDownStartDt().equals(App.getMemDownStartDt())) ) {	infoUpdate += 1;	}
		if( appFormVO.getMemDownEndDt() != null && !(appFormVO.getMemDownEndDt().equals(App.getMemDownEndDt())) ) {	infoUpdate += 1;	}

		if( appFormVO.getCouponGb() != null && !"".equals(appFormVO.getCouponGb()) && !(appFormVO.getCouponGb().equals(App.getCouponGb())) ) {	infoUpdate += 1;	}
		if( appFormVO.getNonmemDownGb() != null && !"".equals(appFormVO.getNonmemDownGb()) && !(appFormVO.getNonmemDownGb().equals(App.getNonmemDownGb())) ) {	infoUpdate += 1;	}
		if( appFormVO.getNonmemDownAmt() != null  && !"".equals(appFormVO.getNonmemDownAmt()) && !(appFormVO.getNonmemDownAmt().equals(App.getNonmemDownAmt())) )  {	infoUpdate += 1;	}
		if( appFormVO.getNonmemDownStarDt() != null && !(appFormVO.getNonmemDownStarDt().equals(App.getNonmemDownStarDt())) ) {	infoUpdate += 1;	}
		if( appFormVO.getNonmemDownEndDt() != null && !(appFormVO.getNonmemDownEndDt().equals(App.getNonmemDownEndDt())) ) {	infoUpdate += 1;	}
		
		return infoUpdate;
	}
	//20180327 - lsy : develop version managemenet - end

	@Override
	public App selectBeforAppInfoBySeqForNewVersion(int app_seq) {
		// TODO Auto-generated method stub
		return dao.selectBeforAppInfoBySeqForNewVersion( "selectBeforAppInfoBySeqForNewVersion", app_seq );
	}

	@Override
	public int insertNewVersionAppInfo(App newVersionAppInfo) {
		// TODO Auto-generated method stub
		return dao.insertNewVersionAppInfo("insertNewVersionAppInfo", newVersionAppInfo);
	}

	@Override
	public App selectAppByAppSeqForMakeJobTicket(int app_seq) {
		// TODO Auto-generated method stub
		return dao.selectAppByAppSeqForMakeJobTicket("selectAppByAppSeqForMakeJobTicket", app_seq);
	}

	@Override
	public int duplicateVerCheck(Entity param) {
		// TODO Auto-generated method stub
		return dao.duplicateVerCheck("duplicateVerCheck", param);
	}

	@Override
	public String selectAppVersionMax(int app_seq) {
		// TODO Auto-generated method stub
		return dao.selectAppVersionMax("selectAppVersionMax", app_seq);
	}
}
