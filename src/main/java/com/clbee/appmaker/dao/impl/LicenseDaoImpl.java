package com.clbee.appmaker.dao.impl;

import com.clbee.appmaker.Json.ConnectLicenseInfo;
import com.clbee.appmaker.dao.LicenseDao;
import com.clbee.appmaker.model.*;
import com.clbee.appmaker.model.list.LicenseList;
import com.clbee.appmaker.util.Entity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Repository
@Transactional
public class LicenseDaoImpl implements LicenseDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int checkUseLicense(String DBName, int userSeq) {
		return sqlSession.selectOne(DBName, userSeq);
	}

	@Override
	public List<License> selectList(String DBName, LicenseList licenseList) {
		return sqlSession.selectList(DBName, licenseList);
	}

	@Override
	public int totalCount(String DBName, LicenseList licenseList ){
		return sqlSession.selectOne(DBName, licenseList);
	}

	@Override
	public int dupleCheck(String DBName, String license) {
		return sqlSession.selectOne(DBName, license);
	}

	@Override
	public void insertLicense(String DBName, License license) {
		sqlSession.insert(DBName, license);
	}

	@Override
	public int disposalLicense(String DBName, Entity param) {
		return sqlSession.update(DBName, param);
	}

	@Override
	public List<License> licenseRegistCheck(String DBName, String license) {
		return sqlSession.selectList(DBName, license);
	}

	@Override
	public void licenseRegist(String DBName, License license) {
		sqlSession.update(DBName, license);
	}

	@Override
	public List<License> selectMyLicense(String DBName, int userSeq) {
		return sqlSession.selectList(DBName, userSeq);
	}

	@Override
	public int licenseExpire(String DBName, int licenseSeq) {
		return sqlSession.update(DBName, licenseSeq);
	}

	@Override
	public void licenseExpireEveryday(String DBName) {
		sqlSession.update(DBName);
	}

	@Override
	public int totalCountDevice(String DBName, LicenseSubList licenseUseDevice) {
		return sqlSession.selectOne(DBName, licenseUseDevice);
	}

	@Override
	public List<LicenseSub> selectListDevice(String DBName, LicenseSubList licenseUseDevice) {
		return sqlSession.selectList(DBName, licenseUseDevice);
	}

	@Override
	public int deleteLicenseUseDevice(String DBName, int licensesubSeq) {
		return sqlSession.delete(DBName, licensesubSeq);
	}

	@Override
	public int deleteLicenseSub(String DBName, int licenseSeq) {
		return sqlSession.delete(DBName, licenseSeq);
	}

	@Override
	public List<Member> checkAccountStatus(String DBName, ConnectLicenseInfo connectLicenseInfo) {
		return sqlSession.selectList(DBName, connectLicenseInfo);
	}

	@Override
	public List<License> checkRegistLicenseWithAccount(String DBName, Member account) {
		return sqlSession.selectList(DBName, account);
	}

	@Override
	public void licenseUserRegist(String DBName, ConnectLicenseInfo connectLicenseInfo) {
		sqlSession.insert(DBName, connectLicenseInfo);
	}

	@Override
	public String selectLicenseDisposalReason(String DBName, int licenseSeq) {
		return sqlSession.selectOne(DBName, licenseSeq);
	}

	@Override
	public int getLicenseUserCount(String DBName, int licenseSeq) {
		return sqlSession.selectOne(DBName, licenseSeq);
	}

	@Override
	public boolean checkLicenseUserExist(String DBName, int userSeq) {
		int checkCount = sqlSession.selectOne(DBName, userSeq);
		return checkCount > 0;
	}

	@Override
	public void deleteLicenseSubByUserSeq(String DBName, int userSeq) {
		sqlSession.delete(DBName, userSeq);
	}

	@Override
	public List<ConnectLicenseInfo> licenseAuthCheckWithDevice(String DBName, ConnectLicenseInfo connectLicenseInfo) {
		return sqlSession.selectList(DBName, connectLicenseInfo);
	}

	@Override
	public int checkAccountStatusByUserSeq(String DBName, int userSeq) {
		return sqlSession.selectOne(DBName, userSeq);
	}

	@Override
	public int checkLicenseStatusByLicenseSeq(String DBName, int licenseSeq) {
		return sqlSession.selectOne(DBName, licenseSeq);
	}

	@Override
	public List<HashMap<String, String>> licenseExpireAlertEveryday(String DBName) {
		return sqlSession.selectList(DBName);
	}

	@Override
	public String selectLicenseUseCompanyName(String DBName, int userSeq) {
		return sqlSession.selectOne(DBName, userSeq);
	}
	
}