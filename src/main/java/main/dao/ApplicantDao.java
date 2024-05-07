package main.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import main.entity.Applicant;

@Service
public interface ApplicantDao {	
	int insert(Applicant applicant) throws SQLException;	
}
