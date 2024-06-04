package main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import main.dao.domMemberDao;
import main.entity.domMember;

@Controller
@RequestMapping("/")
public class homeController {
	
	@Autowired
	domMemberDao domMemberDao;
	
	
	@RequestMapping()
	public String index() {
		return "index";
	}
	
	
	@RequestMapping("auditPageAdmin")
	public String auditPageAdmin() {
		return "auditPageAdmin";
	
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
		return "showRoomStatus";
	}
}
