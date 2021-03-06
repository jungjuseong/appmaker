package com.clbee.appmaker.dao.impl;

import com.clbee.appmaker.dao.GroupViewMenuDao;
import com.clbee.appmaker.model.GroupMenu;
import com.clbee.appmaker.model.GroupUser;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GroupViewMenuDaoImpl implements GroupViewMenuDao {

	@Autowired
	private SqlSession sqlSession;
	
	public GroupUser selectViewMenuInfo(String DBName, String groupSeq ){
		return sqlSession.selectOne(DBName, Integer.parseInt(groupSeq));
	}
	
	public List<GroupMenu> selectMenu(String DBName, String menu_type, List<Integer> menu_large ){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuType", menu_type);
		paramMap.put("menuLarge", menu_large);
		
		return sqlSession.selectList(DBName, paramMap);
	}
}