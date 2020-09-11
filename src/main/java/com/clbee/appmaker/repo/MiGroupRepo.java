package com.clbee.appmaker.repo;

import com.clbee.appmaker.model.GroupList;
import com.clbee.appmaker.model.GroupMenu;
import com.clbee.appmaker.model.GroupUser;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MiGroupRepo implements GroupRepo {

	@Autowired
	private SqlSession sqlSession;
	
  	@Override
	public List<GroupMenu> selectMenu(String DBName, String menu_type ) {
		// TODO Auto-generated method stub		
		return sqlSession.selectList(DBName, menu_type);
	}

	@Override
	public void insertGroupUser(String DBName, GroupUser groupUser) {
		// TODO Auto-generated method stub
		sqlSession.insert(DBName, groupUser);
	}

	@Override
	public List<GroupUser> selectList(String DBName, GroupList groupList ) {
		// TODO Auto-generated method stub		
		return sqlSession.selectList(DBName, groupList);
	}

	@Override
	public int totalCount( String DBName, GroupList groupList ){
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, groupList);
	}

	@Override
	public int groupNameOverlap(String DBName, String groupName) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, groupName);
	}

	@Override
	public int deleteCheck(String DBName, int numGroupSeq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, numGroupSeq);
	}

	@Override
	public int deleteGroup(String DBName, int numGroupSeq) {
		// TODO Auto-generated method stub
		return sqlSession.delete(DBName, numGroupSeq);
	}

	@Override
	public GroupUser selectGroupInfo(String DBName, int groupSeq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(DBName, groupSeq);
	}

	@Override
	public void updateGroupUser(String DBName, GroupUser groupUser) {
		// TODO Auto-generated method stub
		sqlSession.update(DBName, groupUser);
	}

	@Override
	public List<GroupUser> getSelectListGroup(String DBName, int companySeq) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(DBName, companySeq);
	}
}