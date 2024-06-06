package main.service.jdbc;

import org.springframework.beans.factory.annotation.Autowired;

import main.entity.Account;
import main.service.accountService;
import main.mapperDao.accountMapper;
public class accountServiceImple implements accountService{

	@Autowired
	accountMapper accountMapper;
	
	@Override
	public void join(Account account) {
		accountMapper.insertAccount(account);
		
	}

}
