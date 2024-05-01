package main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class homeController {
	
	@RequestMapping("auditPageAdmin")
	public String auditPageAdmin() {
		return "auditPageAdmin";
	
	}
	
	@RequestMapping("auditMember")
	public String auditMeber() {
		return "admin/auditMember";
	}
}
