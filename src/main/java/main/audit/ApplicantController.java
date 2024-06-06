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
	
	@RequestMapping("auditPageAdmin")
	public String auditPageAdmin() {
		return "auditPageAdmin";
	
	}

}
