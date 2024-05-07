package main.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import main.entity.Applicant;
import main.entity.domMember;
import main.entity.roomEntity;

@Service
public interface domMemberDao {
	Applicant returnMember(String stuNumber);

	List<roomEntity> getRemainRoom(Map<String,String> creterionRoom);
	int insertDomMember(Map<String,Integer> member);
	
}
