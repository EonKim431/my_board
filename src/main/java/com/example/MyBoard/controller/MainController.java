package com.example.MyBoard.controller;

import com.example.MyBoard.dto.ArticleDto;
import com.example.MyBoard.repositary.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    private final ArticleService articleService;

    public MainController(ArticleService articleService) {
        this.articleService = articleService;
    }
/*    @GetMapping("/")
    public String testView(Model model){
        model.addAttribute("name","김언");
        return "articles/show_all";
    }*/
    @GetMapping("/")
    public String showAll(Model model){
        List<ArticleDto> list = articleService.findAll();
        model.addAttribute("dtos",list);
        return "articles/show_all";
    }
}
