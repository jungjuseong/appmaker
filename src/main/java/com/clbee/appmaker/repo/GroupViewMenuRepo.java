package com.clbee.appmaker.repo;

import com.clbee.appmaker.model.GroupMenu;
import com.clbee.appmaker.model.GroupUser;

import java.util.List;

public interface GroupViewMenuRepo {
	GroupUser selectViewMenuInfo( String DBName, String groupSeq );
	List<GroupMenu> selectMenu( String DBName, String menu_type, List<Integer> menu_large );
}