package com.clbee.appmaker.repo;

import com.clbee.appmaker.Json.ConnectLicenseInfo;
import com.clbee.appmaker.model.*;
import com.clbee.appmaker.util.Entity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MiLicenseRepo implements LicenseRepo {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int checkUseLicense(String DBName, int userSeq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, userSeq);
	}

	@Override
	public List<License> selectList(String DBName, LicenseList licenseList) {
		// TODO Auto-generated method stub		
		return sqlSession.selectList(DBName, licenseList);
	}

	@Override
	public int totalCount( String DBName, LicenseList licenseList ){
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, licenseList);
	}

	@Override
	public int dupleCheck(String DBName, String license) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, license);
	}

	@Override
	public void insertLicense(String DBName, License license) {
		// TODO Auto-generated method stub
		sqlSession.insert(DBName, license);
	}

	@Override
	public int disposalLicense(String DBName, Entity param) {
		// TODO Auto-generated method stub
		return sqlSession.update(DBName, param);
	}

	@Override
	public List<License> licenseRegistCheck(String DBName, String license) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(DBName, license);
	}

	@Override
	public void licenseRegist(String DBName, License license) {
		// TODO Auto-generated method stub
		sqlSession.update(DBName, license);
	}

	@Override
	public List<License> selectMyLicense(String DBName, int userSeq) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(DBName, userSeq);
	}

	@Override
	public int licenseExpire(String DBName, int licenseSeq) {
		// TODO Auto-generated method stub
		return sqlSession.update(DBName, licenseSeq);
	}

	@Override
	public void licenseExpireEveryday(String DBName) {
		// TODO Auto-generated method stub
		sqlSession.update(DBName);
	}

	@Override
	public int totalCountDevice(String DBName, LicenseSubList licenseUseDevice) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, licenseUseDevice);
	}

	@Override
	public List<LicenseSub> selectListDevice(String DBName, LicenseSubList licenseUseDevice) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(DBName, licenseUseDevice);
	}

	@Override
	public int deleteLicenseUseDevice(String DBName, int licensesubSeq) {
		// TODO Auto-generated method stub
		return sqlSession.delete(DBName, licensesubSeq);
	}

	@Override
	public int deleteLicenseSub(String DBName, int licenseSeq) {
		// TODO Auto-generated method stub
		return sqlSession.delete(DBName, licenseSeq);
	}

	@Override
	public List<Member> checkAccountStatus(String DBName, ConnectLicenseInfo connectLicenseInfo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(DBName, connectLicenseInfo);
	}

	@Override
	public List<License> checkRegistLicenseWithAccount(String DBName, Member account) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(DBName, account);
	}

	@Override
	public void licenseUserRegist(String DBName, ConnectLicenseInfo connectLicenseInfo) {
		// TODO Auto-generated method stub
		sqlSession.insert(DBName, connectLicenseInfo);
	}

	@Override
	public String selectLicenseDisposalReason(String DBName, int licenseSeq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, licenseSeq);
	}

	@Override
	public int getLicenseUserCount(String DBName, int licenseSeq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, licenseSeq);
	}

	@Override
	public boolean checkLicenseUserExist(String DBName, int userSeq) {
		// TODO Auto-generated method stub
		int checkCount = sqlSession.selectOne(DBName, userSeq);
		if(checkCount > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void deleteLicenseSubByUserSeq(String DBName, int userSeq) {
		// TODO Auto-generated method stub
		sqlSession.delete(DBName, userSeq);
	}

	@Override
	public List<ConnectLicenseInfo> licenseAuthCheckWithDevice(String DBName, ConnectLicenseInfo connectLicenseInfo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(DBName, connectLicenseInfo);
	}

	@Override
	public int checkAccountStatusByUserSeq(String DBName, int userSeq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, userSeq);
	}

	@Override
	public int checkLicenseStatusByLicenseSeq(String DBName, int licenseSeq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, licenseSeq);
	}

	@Override
	public List<HashMap<String, String>> licenseExpireAlertEveryday(String DBName) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(DBName);
	}

	@Override
	public String selectLicenseUseCompanyName(String DBName, int userSeq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, userSeq);
	}
	
}