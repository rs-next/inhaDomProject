package main.audit;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import main.entity.Applicant;
import main.service.ApplicantService;
import main.service.adminService;
import main.service.domMemberService;

import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/view/")
public class ApplicantController {
	
	
	private ApplicantService applicantService;	
	@Autowired
	private domMemberService domMemberService; 
	@Autowired
	private adminService adminService;
	
	public ApplicantController(@Qualifier("adminService") main.service.adminService adminService) {
		super();
		this.adminService = adminService;
	}

	@Autowired	
	public ApplicantController(@Qualifier("ApplicantService") ApplicantService applicantService) {
		super();
		this.applicantService = applicantService;
	}

	@RequestMapping(value="ApplicantInsert", method=RequestMethod.POST)
	public String ApplicantInsert(@ModelAttribute Applicant applicant, Model model) throws SQLException {
		applicantService.insert(applicant);		
		 System.out.print(applicant.toString());
		 model.addAttribute("applicant", applicant);
		return "ApplicantInsert";
	}
	
	@RequestMapping(value="admin/auditAction", method=RequestMethod.POST)
	public String auditAction(
			Model model, 
			@RequestParam("stuGender") String stuGender, 
			@RequestParam("roomType") String roomType) throws SQLException, IOException {
		List<Applicant> completApplicant =  adminService.audit(roomType, stuGender);		
		model.addAttribute("completApplicant", completApplicant);
		System.out.println("completApplicant.toString() : "+completApplicant.toString());		
		return "admin/auditAction";
	}
	
	@RequestMapping(value="admin/insertDomMember",method=RequestMethod.POST)
	public String insertDomMember(
			Model model,
	        @RequestParam("stuNum[]") List<String> stuNum) {
		Map<Integer,Integer> domMemberList = domMemberService.insertMember(stuNum);
		model.addAttribute("domMember", domMemberList);
		return "admin/insertDomMember";
		
	}
	

}
