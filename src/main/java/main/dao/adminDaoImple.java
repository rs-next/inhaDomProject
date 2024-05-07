package main.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.entity.Applicant;
import main.entity.DuplicateEntity;

@Repository
public class adminDaoImple implements adminDao {

	
	SqlSession sqlSession;
	Applicant applicant;
	
	@Autowired
	public adminDaoImple(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	@Override
	public Applicant selectLastStudent(Map<String,Object> auditCreterion) throws SQLException, IOException {		
		return sqlSession.selectOne("main.entity.selectLastStudent",auditCreterion);
	}

	@Override
	public List<Applicant> selectDupliStudent(Map<String, Object> auditCreterion) {		
		return sqlSession.selectList("main.entity.selectDupliStudent",auditCreterion);
	}
	@Override
	public List<Applicant> selectAcceptStudent(Map<String, Object> auditCreterion) {
		return sqlSession.selectList("main.entity.selectAcceptStudent",auditCreterion);
	}

	@Override
	public Applicant selectAddiStudent(int stuNum) {
		return sqlSession.selectOne("main.entity.selectAddiStudent",stuNum);
	}

	@Override
	public List<Applicant> selectChosenStudent(Applicant applicant) {
		return sqlSession.selectList("main.entity.selectChosenStudent",applicant);
		
	}

}
