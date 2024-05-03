package com.example.MyBoard.repositary.service;

import com.example.MyBoard.constant.UserRole;
import com.example.MyBoard.dto.UserCreateForm;
import com.example.MyBoard.entity.UserAccount;
import com.example.MyBoard.repositary.UserAccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EntityManager em;

    @Autowired
    UserAccountRepository userAccountRepository;



    public void createUser(UserCreateForm userCreateForm) {
        UserAccount account = new UserAccount();
        account.setUserName(userCreateForm.getUserName());
        account.setEmail(userCreateForm.getEmail());
        account.setPassword(passwordEncoder.encode(userCreateForm.getPassword()));
        account.setNickName(userCreateForm.getNickName());
        if ("ADMIN".equals(userCreateForm.getUserName().toUpperCase())){
            account.setUserRole(UserRole.ADMIN);
        }else {
            account.setUserRole(UserRole.USER);
        }
        em.persist(account);
    }
}
