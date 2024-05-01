package com.example.MyBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LayoutTestController {
    @GetMapping("/test")
    public String testView(Model model){
        model.addAttribute("name","김언");
        return "test";
    }
    @GetMapping("/show_all")
    public String showAll(Model model){
        return "articles/show_all";
    }
}
