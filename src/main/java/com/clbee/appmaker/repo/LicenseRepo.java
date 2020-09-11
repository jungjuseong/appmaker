package com.clbee.appmaker.repo;

import com.clbee.appmaker.Json.ConnectLicenseInfo;
import com.clbee.appmaker.model.*;
import com.clbee.appmaker.util.Entity;

import java.util.HashMap;
import java.util.List;

public interface LicenseRepo {
	int checkUseLicense( String DBName, int userSeq );
	List<License> selectList( String DBName, LicenseList licenseList );
	int totalCount( String DBName, LicenseList licenseList );
	int dupleCheck( String DBName, String license );
	void insertLicense( String DBName, License license );
	int disposalLicense( String DBName, Entity param );
	List<License> licenseRegistCheck( String DBName, String license );
	void licenseRegist( String DBName, License vo);

	List<License> selectMyLicense( String DBName, int userSeq );
	int licenseExpire( String DBName, int licenseSeq );
	void licenseExpireEveryday( String DBName );

	int totalCountDevice( String DBName, LicenseSubList licenseUseDevice );
	List<LicenseSub> selectListDevice(String DBName, LicenseSubList licenseUseDevice );
	int deleteLicenseUseDevice( String DBName, int licensesubSeq );
	int deleteLicenseSub( String DBName, int licenseSeq );

	List<Member> checkAccountStatus(String DBName, ConnectLicenseInfo connectLicenseInfo );
	List<License> checkRegistLicenseWithAccount( String DBName, Member account );	
	void licenseUserRegist( String DBName, ConnectLicenseInfo connectLicenseInfo);
	List<ConnectLicenseInfo> licenseAuthCheckWithDevice( String DBName, ConnectLicenseInfo connectLicenseInfo );
	
	int checkAccountStatusByUserSeq( String DBName, int userSeq );
	int checkLicenseStatusByLicenseSeq( String DBName, int licenseSeq );
	
	String selectLicenseDisposalReason( String DBName, int licenseSeq );
	int getLicenseUserCount( String DBName, int licenseSeq );

	boolean checkLicenseUserExist( String DBName, int userSeq );
	void deleteLicenseSubByUserSeq( String DBName, int userSeq );
	List<HashMap<String, String>> licenseExpireAlertEveryday(String DBName);
	
	String selectLicenseUseCompanyName( String DBName, int userSeq );
}