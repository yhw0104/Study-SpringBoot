package com.example.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class articleController {

    @GetMapping("/articles/new")
    public String newArticleForm() {

        return "articles/new";
    }
}
