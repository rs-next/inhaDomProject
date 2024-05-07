package main.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import main.entity.Applicant;
import main.entity.DuplicateEntity;

@Service
public interface adminDao{	
	Applicant selectLastStudent(Map<String, Object> auditCreterion) throws SQLException, IOException;
	List<Applicant> selectDupliStudent(Map<String, Object> auditCreterion);	
	List<Applicant> selectChosenStudent(Applicant applicant);
	List<Applicant> selectAcceptStudent(Map<String, Object> auditCreterion);
	Applicant selectAddiStudent(int stuNum);
}
