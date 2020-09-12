package com.clbee.appmaker.dao;

import com.clbee.appmaker.model.Device;
import java.util.List;

public interface DeviceDao {
	int insertDeviceInfo(Device device );
	int updateDeviceInfo(Device device );
	Device selectDeviceInfo ( int deviceSeq );
	List<Device> selectDeviceList(int startNo, int companySeq, String searchType, String searchValue);
	int selectDeviceListCount( int companySeq, String searchType, String searchValue);
	int checkIfExistUUID ( String deviceUuid, int companySeq );
	int countDeviceIsAvailable ( int companySeq );
}
