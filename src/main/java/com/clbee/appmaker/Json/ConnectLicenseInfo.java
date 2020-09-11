package com.clbee.appmaker.Json;

import lombok.Data;

@Data
public class ConnectLicenseInfo {
	String workPath;
	String userId;
	String userPwd;
	String deviceName;
	String deviceOs;
	String deviceSeq;
	String resultCode;

	int userSeq;
	int licenseSeq;
	int userCopyCount;
}