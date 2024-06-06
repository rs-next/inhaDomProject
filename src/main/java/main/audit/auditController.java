package main.audit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import main.dao.domMemberDao;
import main.entity.Applicant;
import main.entity.domMember;
import main.service.adminService;
import main.service.domMemberService;

@Controller
@RequestMapping("/admin/")
public class auditController {
	
	@Autowired
	private domMemberService domMemberService;
	@Autowired
	private domMemberDao domMemberDao;
	@Autowired
	private adminService adminService;
	
	public auditController(@Qualifier("adminService") main.service.adminService adminService) {
		super();
		this.adminService = adminService;
	}
	
	@RequestMapping("auditMember")
	public String auditMeber() {
		return "admin/auditMember";
	}
	
	@RequestMapping("showRoomStatus")
	public String showRoomStatus(Model model) {
		List<domMember> domMembers = new ArrayList<>();
		domMembers = domMemberDao.selectAllDomMember();
		model.addAttribute("domMembers",domMembers);
		System.out.println("가져는왔니?");
		return "admin/showRoomStatus";
	}
	

	@RequestMapping(value="auditAction", method=RequestMethod.POST)
	public String auditAction(
			Model model, 
			@RequestParam("stuGender") String stuGender, 
			@RequestParam("roomType") String roomType) throws SQLException, IOException {
		List<Applicant> completApplicant =  adminService.audit(roomType, stuGender);		
		model.addAttribute("completApplicant", completApplicant);
		System.out.println("completApplicant.toString() : "+completApplicant.toString());		
		return "admin/auditAction";
	}
	
	@RequestMapping(value="insertDomMember",method=RequestMethod.POST)
	public String insertDomMember(
			Model model,
	        @RequestParam("stuNum[]") List<String> stuNum) {
		Map<Integer,Integer> domMemberList = domMemberService.insertMember(stuNum);
		model.addAttribute("domMember", domMemberList);
		return "admin/insertDomMember";
		
	}
}
