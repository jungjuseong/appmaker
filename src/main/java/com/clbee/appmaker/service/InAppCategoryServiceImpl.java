package com.clbee.appmaker.service;

import com.clbee.appmaker.dao.InAppCategoryDao;
import com.clbee.appmaker.model.InAppCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InAppCategoryServiceImpl implements InAppCategoryService {

	@Autowired
	private InAppCategoryDao dao;

	@Override
	public int insertInAppInfo(InAppCategory inCateVo) {
		return dao.insertInAppInfo(inCateVo);
	}

	@Override
	public List<InAppCategory> selectInAppList(InAppCategory inCateVo) {
		
		List<InAppCategory> Result = null;
		
		try{		
			Result = dao.selectInAppList(inCateVo);
		}catch(Exception e){
			e.printStackTrace();
		}			
		return Result;
	}

	@Override
	public void updateInAppInfo(InAppCategory inCateVo) {
		 dao.updateInAppInfo(inCateVo);		
	}

	@Override
	public void deleteInAppInfo(InAppCategory inCateVo) {
		dao.deleteInAppInfo(inCateVo);		
		
	}
	
	@Override
	public Object[] selectInAppList2(InAppCategory inCateVo) {
		
		Object[] Result = null;
		
		try{		
			Result = dao.selectInAppList2(inCateVo);
		}catch(Exception e){
			e.printStackTrace();
		}			
		return Result;
	}

	@Override
	public InAppCategory findByCustomInfo(String DBName, int intValue) {
		return dao.findByCustomInfo(DBName, intValue);
	}

	@Override
	public InAppCategory findByCustomInfo(String DBName, String Value) {
		return dao.findByCustomInfo(DBName, Value);
	}

	@Override
	public List<InAppCategory> getListInAppCategory(String DBName, String storeBundleId) {
		return dao.getListInAppCategory(DBName, storeBundleId);
	}

	@Override
	public List<InAppCategory> getListInAppCategoryForOneDepth(String DBName,
			String storeBundleId) {
		return dao.getListInAppCategoryForOneDepth(DBName, storeBundleId);
	}

	@Override
	public int categoryIsDuplicated(String storeBundleId, String categoryName) {
		return dao.categoryIsDuplicated(storeBundleId, categoryName);
	}
	
}
