package com.example.springstudy.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 일반 Controller는 뷰 템플릿을 반환하지만, RestController는 JSON(데이터)을 반환하는 RestAPI Controller
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello(){
        return "hello world!";
    }
}
