package com.clbee.appmaker.model.list;

import com.clbee.appmaker.model.Device;
import lombok.Data;

import java.util.List;

@Data
public class DeviceList {
	private List<Device> list;
	private int pageSize;
	private int maxResult;
	private int totalCount;
	private int totalPage;
	private int currentPage;
	private int startNo;
	private int endNo;
	private int startPage;
	private int endPage;
	
	public DeviceList(int pageSize, int totalCount, int currentPage, int maxResult) {
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.maxResult = maxResult;
		calc();
	}
	
	private void calc(){
		totalPage = (totalCount-1)/maxResult + 1;
		currentPage = currentPage>totalPage ? totalPage : currentPage;
		startNo = (currentPage-1) * pageSize;
		endNo = startNo + pageSize - 1;
		endNo = endNo>totalCount ? totalCount : endNo;
		startPage = ((currentPage-1)/pageSize) * pageSize + 1;
		endPage = startPage + pageSize -1;
		endPage  = endPage>totalPage ? totalPage : endPage;
	}
}

