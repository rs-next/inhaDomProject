package main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.security.securityAccount;
import main.dao.domMemberDao;
import main.entity.Account;
import main.entity.domMember;
import main.service.accountService;
import main.service.jdbc.accountServiceImple;

@Controller
@RequestMapping("/")
public class homeController {

	
	@Autowired
	accountService accountService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String goLogin() {
		return "login";
	}

	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String goJoin() {
		return "join";
	}
	
	@RequestMapping("accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
	
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String join(Account account) {
		account.setDomPW(new BCryptPasswordEncoder().encode(account.getDomPW()));
		accountService.join(account);
		return "login";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String goHome(Model model, Authentication authentication) {
		if(authentication != null){
			securityAccount account = (securityAccount)authentication.getPrincipal();
			model.addAttribute("username", account.getUsername());
		}
		return "login";
	}
	
	
	 

}
