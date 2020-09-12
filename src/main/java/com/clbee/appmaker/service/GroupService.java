package com.clbee.appmaker.service;

import com.clbee.appmaker.model.list.GroupList;
import com.clbee.appmaker.model.GroupMenu;
import com.clbee.appmaker.model.GroupUser;

import java.util.List;

//20180424 : lsy - group management create

public interface GroupService {	
	List<GroupMenu> selectMenu(String menu_type);
	void insertGroupUser(GroupUser groupUser);
	GroupList selectList(GroupList groupList);
	int groupNameOverlap (String groupName);
	int deleteGroup(int numGroupSeq);
	GroupUser selectGroupInfo(int groupSeq);
	void updateGroupUser(GroupUser groupUser);
	List<GroupUser> getSelectListGroup(int companySeq );
}