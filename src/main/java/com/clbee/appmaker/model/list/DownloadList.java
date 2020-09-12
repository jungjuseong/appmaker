package com.clbee.appmaker.model.list;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DownloadList {
	private List<?> list;			// 1������ �з�
	private int pageSize;			// �ѹ��� ������ �ִ� ����������
	private int maxResult;			// �������� ����Ʈ ����
	private int totalCount;			// ��ü ����
	private int totalPage;			// ��ü ������
	private Integer currentPage;	// ���� ������
	private int startNo;			// ���� �۹�ȣ
	private int endNo;				// ���۹�ȣ
	private int startPage;			// ���� ������ ��ȣ
	private int endPage;			// ������ ������ ��ȣ
	
	private String searchGubun;	

	private String searchType;
	private String searchValue;

	public DownloadList(int pageSize, int totalCount, Integer currentPage, int maxResult) {
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
	public void calc(int pageSize, int totalCount, Integer currentPage, int maxResult){
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.maxResult = maxResult;	
		totalPage = (totalCount-1)/maxResult + 1;
		currentPage = currentPage>totalPage ? totalPage : currentPage;
		startNo = (currentPage-1) * pageSize;
		endNo = startNo + pageSize - 1;
		endNo = endNo>totalCount ? totalCount : endNo;
		startPage = ((currentPage-1)/pageSize) * pageSize + 1;
		endPage = startPage + pageSize -1;
		endPage  = endPage>totalPage ? totalPage : endPage;
	}

	@Override
	public String toString() {
		return "DownloadList [list=" + list + ", pageSize=" + pageSize
				+ ", maxResult=" + maxResult + ", totalCount=" + totalCount
				+ ", totalPage=" + totalPage + ", currentPage=" + currentPage
				+ ", startNo=" + startNo + ", endNo=" + endNo + ", startPage="
				+ startPage + ", endPage=" + endPage + ", searchType="
				+ searchType + ", searchValue=" + searchValue + "]";
	}


}
