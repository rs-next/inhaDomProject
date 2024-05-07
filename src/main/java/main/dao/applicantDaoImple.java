package main.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.entity.Applicant;

@Repository
public class applicantDaoImple implements ApplicantDao{

	
	SqlSession sqlSession;
	
	@Autowired
	public applicantDaoImple(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	@Override
	public int insert(Applicant applicant) throws SQLException {
		return sqlSession.insert("main.entity.insertApplicant",applicant);
	}

}
