package com.clbee.appmaker.service;

import com.clbee.appmaker.dao.DeviceDao;
import com.clbee.appmaker.model.list.DeviceList;
import com.clbee.appmaker.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
	
	@Autowired
	DeviceDao deviceDao;

	@Override
	public int insertDeviceInfo(Device device) {
		// TODO Auto-generated method stub
		return deviceDao.insertDeviceInfo(device);
	}

	@Override
	public int updateDeviceInfo(Device Device) {
		// TODO Auto-generated method stub
		return deviceDao.updateDeviceInfo(Device);
	}

	@Override
	public Device selectDeviceInfo(int deviceSeq) {
		// TODO Auto-generated method stub
		return deviceDao.selectDeviceInfo(deviceSeq);
	}

	@Override
	public DeviceList selectDeviceList(int currentPage, int companySeq, String searchType, String searchValue) {
		// TODO Auto-generated method stub
		
		
		DeviceList list = null;
		// ��ü ������ 1,2,3,4,5,6,7,8,9,19�ؿ� �Ķ���� ���ڰ� ǥ�õ� ����
		int pageSize = 10;
		// ���������� �ִ� �����õ� Ƚ��
		int maxResult = 10; 
		int totalCount = 0;
		int startNo = 0;
		
			try{
				totalCount = deviceDao.selectDeviceListCount(companySeq, searchType, searchValue);
				System.out.println("totalCount = " + totalCount);
				
				list = new DeviceList(pageSize, totalCount, currentPage, maxResult);
			
				startNo = (currentPage-1) *maxResult;
	
				List<Device> vo = deviceDao.selectDeviceList(startNo, companySeq, searchType, searchValue);
				
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
	public int checkIfExistUUID(String deviceUuid, int companySeq) {
		// TODO Auto-generated method stub
		return deviceDao.checkIfExistUUID(deviceUuid, companySeq);
	}

	@Override
	public int countDeviceIsAvailable(int companySeq) {
		// TODO Auto-generated method stub
		return deviceDao.countDeviceIsAvailable(companySeq);
	}
}
