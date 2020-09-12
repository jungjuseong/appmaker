package com.clbee.appmaker.service;

import com.clbee.appmaker.model.list.DeviceList;
import com.clbee.appmaker.model.Device;

public interface DeviceService {

	int insertDeviceInfo( Device device );
	int updateDeviceInfo( Device device );
	Device selectDeviceInfo ( int deviceSeq );
	DeviceList selectDeviceList(int currentPage, int companySeq, String searchType, String searchValue);
	int checkIfExistUUID ( String deviceUuid, int companySeq );
	int countDeviceIsAvailable(int companySeq);
}
