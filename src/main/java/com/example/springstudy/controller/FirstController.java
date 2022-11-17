package com.example.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")    // localhost:8080/hi 페이지를 받을 때 greetings.mustache 파일을 리턴한다. (Controller)
    public String greeting(Model model){    //템플릿 변수(mustache)를 위해 모델을 파라미터로 넣고 (Model)
        model.addAttribute("username", "hyunwoo"); // 모델이라는 객체가 보내준다. username이 윤현우라는 이름으로 반영됨
        return "greetings"; // templates/greetings.mustache 파일 -> 브라우저로 전송 (View)
    }

    @GetMapping("/bye")     // -> GetMapping으로 /bye 페이지를 받으면
    public String goodBye(Model model) {
        model.addAttribute("nickname", "윤현우");  // --> Model 객체로 데이터를 받아서
        return "goodbye";   // ---> View로 페이지를 뿌려준다
    }
}
