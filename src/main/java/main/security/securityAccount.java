package main.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import main.entity.Account;

public class securityAccount extends User{

	public securityAccount(Account account) {
		super(
				account.getDomID(),
				account.getDomPW(),
				AuthorityUtils.createAuthorityList(account.getRole())						
				);
	}
}
