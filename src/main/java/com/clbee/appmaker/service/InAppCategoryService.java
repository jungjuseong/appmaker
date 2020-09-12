package com.clbee.appmaker.service;

import com.clbee.appmaker.model.InAppCategory;

import java.util.List;

public interface InAppCategoryService {

	int insertInAppInfo(InAppCategory inCateVo);
	List<InAppCategory> selectInAppList(InAppCategory inCateVo);
	void updateInAppInfo(InAppCategory inCateVo);
	void deleteInAppInfo(InAppCategory inCateVo);
	Object[] selectInAppList2(InAppCategory inCateVo);
	InAppCategory findByCustomInfo(String DBName, int intValue);
	InAppCategory findByCustomInfo(String DBName, String Value);
	List<InAppCategory> getListInAppCategoryForOneDepth(String DBName, String storeBundleId);
	List<InAppCategory> getListInAppCategory(String DBName, String storeBundleId);
	int categoryIsDuplicated (String appSeq, String categoryName );
}
