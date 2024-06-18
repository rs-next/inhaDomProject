package main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import main.entity.Account;
import main.mapperDao.accountMapper;

@Service
public class securityAccountService implements UserDetailsService {

    @Autowired
    private accountMapper accountMapper;
    
    @Override
    public UserDetails loadUserByUsername(String domID) throws UsernameNotFoundException {
        Account account = accountMapper.getAccountById(domID);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new securityAccount(account);
    }
}
