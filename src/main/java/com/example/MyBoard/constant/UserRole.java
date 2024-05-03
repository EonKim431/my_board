package com.example.MyBoard.constant;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");
    private final String desc;

    UserRole(String desc) {
        this.desc = desc;
    }
}
