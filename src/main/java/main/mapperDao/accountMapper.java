package main.mapperDao;



//import org.springframework.stereotype.Service;

import main.entity.Account;


public interface accountMapper {
	Account getAccountById(String domID);
	void insertAccount(Account account);
}
