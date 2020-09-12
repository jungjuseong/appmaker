package com.clbee.appmaker.service;

import com.clbee.appmaker.Json.ConnectLicenseInfo;
import com.clbee.appmaker.util.Entity;
import com.clbee.appmaker.model.list.LicenseList;
import com.clbee.appmaker.model.LicenseSubList;
import com.clbee.appmaker.model.License;

import java.util.List;

public interface LicenseService {
	int checkUseLicense(int userSeq );
	LicenseList selectList(LicenseList licenseList );
	int makeLicense(License license );
	int disposalLicense(Entity param );
	int licenseRegistCheck(String license );
	void licenseRegist(int userSeq, String license);
	
	LicenseList selectMyList(LicenseList licenseList, int userSeq );
	List<License> selectLicenseForRenew(int userSeq );
	void licenseExpire(int licenseSeq );
	
	LicenseSubList selectLicenseUseDevice(LicenseSubList licenseUseDevice );
	int deleteLicenseUseDevice(int licensesubSeq );
	
	ConnectLicenseInfo licenseAuthCheckWithAccount(ConnectLicenseInfo connectLicenseInfo );
	ConnectLicenseInfo licenseUserRegist(ConnectLicenseInfo connectLicenseInfo );
	ConnectLicenseInfo licenseAuthCheckWithDevice(ConnectLicenseInfo connectLicenseInfo );
	
	String selectLicenseDisposalReason(int licenseSeq );
	
	String selectLicenseUseCompanyName(int userSeq );
}