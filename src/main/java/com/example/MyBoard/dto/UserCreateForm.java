package com.example.MyBoard.dto;

import com.example.MyBoard.constant.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateForm {
    @Size(min = 3,max = 15)
    @NotEmpty(message = "사용자 ID는 필수입니다")
    private String userName;
    @NotEmpty(message = "비밀번호는 필수입니다")
    private String password;
    @NotEmpty(message = "비밀번호 확인은 필수입니다")
    private String passwordCheck;
    @NotEmpty(message = "이메일은 필수입니다")
    private String email;
    private String nickName;
}
