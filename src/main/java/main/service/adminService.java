package main.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import main.entity.Applicant;
import main.entity.DuplicateEntity;

@Service
public interface adminService {	
	List<Applicant> audit(String roomType, String Gender) throws SQLException, IOException;
	double[] convertAddTolatLng(String stuAddress) throws IOException;	
	List<Applicant> changeDupliEntity(List<DuplicateEntity> DuplicateList, Map<String, Object> auditCreterion) throws SQLException;
	double calByHaversign(double stuLat, double stuLng);
}
