package main.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.entity.Applicant;
import main.entity.domMember;
import main.entity.roomEntity;

@Repository
public class domMemberDaoImple implements domMemberDao{

	SqlSession sqlSession;
	Applicant applicant;
	
	@Autowired
	public domMemberDaoImple(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	@Override
	public Applicant returnMember(String stuNum) {		
		return sqlSession.selectOne("main.domMember.selectDomMember",stuNum);
	}

	@Override
	public List<roomEntity> getRemainRoom(Map<String,String> creterionRoom) {
		return sqlSession.selectList("main.domMember.selectRemainRoom",creterionRoom);
	}

	@Override
	public int insertDomMember(Map<String,Integer> member) {
		return sqlSession.insert("main.domMember.insertDomMember",member);
		
	}

	@Override
	public int updateDomMemberData(Map<String, Integer> member) {
		return sqlSession.update("main.domMember.updateDomMemberData", member);
	}

	@Override
	public List<domMember> selectAllDomMember() {
		return sqlSession.selectList("main.domMember.selectAllDomMember");
	}

}
