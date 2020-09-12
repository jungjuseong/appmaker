package com.clbee.appmaker.dao.impl;

import com.clbee.appmaker.dao.GroupDao;
import com.clbee.appmaker.model.list.GroupList;
import com.clbee.appmaker.model.GroupMenu;
import com.clbee.appmaker.model.GroupUser;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDaoImpl implements GroupDao {

	@Autowired
	private SqlSession sqlSession;
	
  	@Override
	public List<GroupMenu> selectMenu(String DBName, String menu_type ) {
		return sqlSession.selectList(DBName, menu_type);
	}

	@Override
	public void insertGroupUser(String DBName, GroupUser groupUser) {
		sqlSession.insert(DBName, groupUser);
	}

	@Override
	public List<GroupUser> selectList(String DBName, GroupList groupList ) {
		return sqlSession.selectList(DBName, groupList);
	}

	@Override
	public int totalCount(String DBName, GroupList groupList ){
		return sqlSession.selectOne(DBName, groupList);
	}

	@Override
	public int groupNameOverlap(String DBName, String groupName) {
		return sqlSession.selectOne(DBName, groupName);
	}

	@Override
	public int deleteCheck(String DBName, int numGroupSeq) {
		return sqlSession.selectOne(DBName, numGroupSeq);
	}

	@Override
	public int deleteGroup(String DBName, int numGroupSeq) {
		return sqlSession.delete(DBName, numGroupSeq);
	}

	@Override
	public GroupUser selectGroupInfo(String DBName, int groupSeq) {
		return sqlSession.selectOne(DBName, groupSeq);
	}

	@Override
	public void updateGroupUser(String DBName, GroupUser groupUser) {
		sqlSession.update(DBName, groupUser);
	}

	@Override
	public List<GroupUser> getSelectListGroup(String DBName, int companySeq) {
		return sqlSession.selectList(DBName, companySeq);
	}
}