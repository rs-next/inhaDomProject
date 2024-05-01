package main.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import main.entity.Applicant;

@Service
public interface ApplicantService {
	List<Applicant> getList();
	int insert(Applicant applicant) throws SQLException;
	int calAddress_Score(String stuAddress);
	double calGrade_Score(double stuGrade,int stuNum);
	double calTotal_Score(int Address_Score, double Grade_Score, int stuNum);
}
