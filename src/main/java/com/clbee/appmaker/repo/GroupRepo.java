package com.clbee.appmaker.repo;

import com.clbee.appmaker.model.GroupList;
import com.clbee.appmaker.model.GroupMenu;
import com.clbee.appmaker.model.GroupUser;

import java.util.List;

public interface GroupRepo {
	List<GroupMenu> selectMenu(String DBName, String menu_type );
	void insertGroupUser (String DBName, GroupUser groupUser);
	List<GroupUser> selectList(String DBName, GroupList groupList);
	int totalCount(String DBName, GroupList groupList);
	int groupNameOverlap (String DBName, String groupName);
	
	int deleteCheck(String DBName, int numGroupSeq);
	int deleteGroup(String DBName, int numGroupSeq);
	GroupUser selectGroupInfo(String DBName, int groupSeq);
	void updateGroupUser (String DBName, GroupUser groupUser);
	List<GroupUser> getSelectListGroup(String DBName, int companySeq);
}