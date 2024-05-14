package main.service.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.adminDao;
import main.dao.domMemberDao;
import main.entity.Applicant;
import main.entity.domMember;
import main.entity.roomEntity;
import main.service.domMemberService;

@Service
public class domMemberServiceImple implements domMemberService{
	
	domMemberDao domMemberDao;
	
	@Autowired
	public domMemberServiceImple(domMemberDao domMemberDao) {
		super();
		this.domMemberDao = domMemberDao;
	}
	@Override
    public Map<Integer, Integer> insertMember(List<String> stuNum) {

        List<Applicant> completAuditList = new ArrayList<>();
        
        for (String stuNumber : stuNum) {
            Applicant applicant = domMemberDao.returnMember(stuNumber);
            completAuditList.add(applicant);
            System.out.println(applicant.getStuNum());
        }

        
        Map<String, String> creterionRoom = new HashMap<>();
        creterionRoom.put("appliType", completAuditList.get(0).getAppliType());
        creterionRoom.put("stuGender", completAuditList.get(0).getStuGender());
        
        List<roomEntity> emptyRoom = domMemberDao.getRemainRoom(creterionRoom);
        Map<Integer, Integer> defineMember = new HashMap<>();

        
        int memberListCount = 0; 
        int roomNumber;
        int availableSpaces;
        
        for (roomEntity room : emptyRoom) {      
        	roomNumber = room.getRoomNum();
            System.out.println("방 번호 : " + roomNumber);           
            if (room.getRoomNowCount() < room.getRoomCount()) {
                availableSpaces = room.getRoomCount() - room.getRoomNowCount();
                Map<Integer, Integer> roomInfo = new HashMap<>();
                roomInfo.put(roomNumber, availableSpaces);
                for (int i = 0; i < availableSpaces; i++) {
                    if (memberListCount < completAuditList.size()) {
                        Applicant applicant = completAuditList.get(memberListCount);
                        defineMember.put(applicant.getStuNum(), roomNumber);  
                        System.out.println(applicant.getStuNum() + " : " + defineMember.get(applicant.getStuNum()));
                        memberListCount++; 
                    }
                }
            }
        }
        Map<String,Integer> insertMember = new HashMap<>();
        Integer domMemNum;
        Integer roomNum;
        int insertFlag;
        for(Map.Entry<Integer,Integer> member : defineMember.entrySet()) {
        	domMemNum = member.getKey();
        	roomNum = member.getValue();
        	insertMember.put("stuNum",domMemNum);
        	insertMember.put("roomNum",roomNum);
        	insertFlag = domMemberDao.insertDomMember(insertMember);
        	if(insertFlag != 1) System.out.println("등록 에러" + insertMember.get("stuNum"));        	
        	
        }
        

        return defineMember;
    }

}
