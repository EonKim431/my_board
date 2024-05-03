package com.example.MyBoard.entity;

import com.example.MyBoard.constant.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class UserAccount {
    @Id
    @Column(length = 50,name = "user_id")
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(length = 100)
    private String email;
    @Column(length = 50,name = "nickname")
    private String nickName;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

}
