package com.clbee.appmaker.service.impl;

import com.clbee.appmaker.dao.GroupDao;
import com.clbee.appmaker.model.list.GroupList;
import com.clbee.appmaker.model.GroupMenu;
import com.clbee.appmaker.model.GroupUser;
import com.clbee.appmaker.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupDao groupDao;

	int pageSize = 10;
	int maxResult = 10;
	int totalCount = 0;
	int startNo = 0;
	
	@Override
	public List<GroupMenu> selectMenu(String menu_type ) {
		return groupDao.selectMenu("selectMenu", menu_type);
	}

	@Override
	public void insertGroupUser(GroupUser groupUser) {
		// TODO Auto-generated method stub
		groupDao.insertGroupUser("insertGroupUser", groupUser);
	}

	@Override
	public GroupList selectList(GroupList groupList ) {
		// TODO Auto-generated method stub
		List<GroupUser> vo = null;
		
		if(groupList.getCompanySeq() == 0) {//회원인경우
			totalCount = groupDao.totalCount("totalCountMemberGroup", groupList);

			groupList.calc(pageSize, totalCount, groupList.getCurrentPage(), maxResult);

			if(groupList.getSearchValue() == "") {
				groupList.setSearchValue(null);
			}

			 vo = groupDao.selectList("selectListMemberGroup", groupList);
		}else {//사용자인경우
			totalCount = groupDao.totalCount("totalCountUserGroup", groupList);

			groupList.calc(pageSize, totalCount, groupList.getCurrentPage(), maxResult);

			if(groupList.getSearchValue() == "") {
				groupList.setSearchValue(null);
			}

			vo = groupDao.selectList("selectListUserGroup", groupList);
		}
		
		groupList.setList(vo);
		return groupList;
	}

	@Override
	public int groupNameOverlap(String groupName) {
		// TODO Auto-generated method stub
		return groupDao.groupNameOverlap("groupNameOverlap", groupName);
	}
	
	//20180213 - lsy : user delete
	@Override
	public int deleteGroup(int numGroupSeq) {
		int cnt = groupDao.deleteCheck("deleteCheck", numGroupSeq);
		
		if(cnt == 0) {
			return groupDao.deleteGroup("deleteGroup", numGroupSeq);
		}else if(cnt > 0) {
			return 2;
		}else {
			return 0;
		} 
	}

	@Override
	public GroupUser selectGroupInfo(int groupSeq) {
		// TODO Auto-generated method stub
		return groupDao.selectGroupInfo("selectGroupInfo", groupSeq);
	}

	@Override
	public void updateGroupUser(GroupUser groupUser) {
		// TODO Auto-generated method stub
		groupDao.updateGroupUser("updateGroupUser", groupUser);
	}

	@Override
	public List<GroupUser> getSelectListGroup(int companySeq) {
		// TODO Auto-generated method stub
		return groupDao.getSelectListGroup("getSelectListGroup", companySeq);
	}
	
}