package main.mapperDao;

import org.apache.ibatis.annotations.Mapper;

import main.entity.Account;

@Mapper
public interface accountMapper {
	Account getAccountById(String domID);
	void insertAccount(Account account);
}
