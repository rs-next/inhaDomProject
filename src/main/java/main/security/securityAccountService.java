package main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import main.mapperDao.accountMapper;

public class securityAccountService implements UserDetailsService{

	@Autowired
	accountMapper accountMapper;
	
	@Override
	public UserDetails loadUserByUsername(String domID) throws UsernameNotFoundException {
		securityAccount account = new securityAccount(accountMapper.getAccountById(domID));
		return account;
	}

}
