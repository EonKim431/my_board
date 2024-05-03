package com.example.MyBoard.controller;

import com.example.MyBoard.dto.UserCreateForm;
import com.example.MyBoard.repositary.service.UserService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("user")
public class UserAccountController {
    private final UserService userService;

    public UserAccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("signup")
    public String signup(UserCreateForm createForm){
        return "signup";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @PostMapping("signup")
    public String signup(@Valid UserCreateForm userCreateForm,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println(userCreateForm.toString());
            return "signUp";
        }

        if (! userCreateForm.getPassword().equals(userCreateForm.getPasswordCheck())){
            bindingResult.rejectValue("passwordCheck","passwordIncorect",
                    "비밀번호가 일치하지 않습니다");
            return "signUp";
        }

        try{
           userService.createUser(userCreateForm);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("signupFailed","이미 등록된 사용자 입니다");
            return "signup";
        }catch (Exception e){
            bindingResult.reject("signupFailed",e.getMessage());
            return "signup";
        }
        userService.createUser(userCreateForm);
        return "redirect:/";
    }
}
