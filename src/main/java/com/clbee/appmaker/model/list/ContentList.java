package com.clbee.appmaker.model.list;

import com.clbee.appmaker.model.Content;
import lombok.Data;

import java.util.List;

@Data
public class ContentList {
	private List<Content> list;	// 1������ �з�
	private int pageSize;			// �ѹ��� ������ �ִ� ����������
	private int maxResult;			// �������� ����Ʈ ����
	private int totalCount;			// ��ü ����
	private int totalPage;			// ��ü ������
	private int currentPage;		// ���� ������
	private int startNo;			// ���� �۹�ȣ
	private int endNo;				// ���۹�ȣ
	private int startPage;			// ���� ������ ��ȣ
	private int endPage;			// ������ ������ ��ȣ
	
	private String searchType;
	private String searchValue;
	
	public ContentList(int pageSize, int totalCount, int currentPage, int maxResult) {
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.maxResult = maxResult;
		calc();
	}

	private void calc(){
		totalPage = (totalCount-1)/maxResult + 1;
		currentPage = currentPage>totalPage ? totalPage : currentPage;
		startNo = (currentPage-1) * maxResult;
		endNo = startNo + pageSize - 1;
		endNo = endNo>totalCount ? totalCount : endNo;
		startPage = ((currentPage-1)/pageSize) * pageSize + 1;
		endPage = startPage + pageSize -1;
		endPage  = endPage>totalPage ? totalPage : endPage;
	}
}
