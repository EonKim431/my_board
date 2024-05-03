package com.example.MyBoard.repositary.service;

import com.example.MyBoard.config.PrincipalDetails;
import com.example.MyBoard.entity.UserAccount;
import com.example.MyBoard.repositary.UserAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserAccountRepository repository;

    public UserDetailService(UserAccountRepository userAccountRepository) {
        this.repository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> account = repository.findById(username);
        System.out.println(account);
        if (account.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
        }
        UserAccount userAccount = account.get();

        return new PrincipalDetails(userAccount);
    }
}
