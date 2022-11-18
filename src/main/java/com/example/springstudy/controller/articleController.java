package com.example.springstudy.controller;

import com.example.springstudy.dto.ArticleForm;
import com.example.springstudy.entity.Article;
import com.example.springstudy.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class articleController {

    @Autowired  //스프링부트가 미리 생성해 놓은 객체를 가져다가 연결
    private ArticleRepository articleRepository;    // ArticleRepository객체를 사용하기 위해 선언
                                                    // 객체를 구현할 필요가 없다 -> 스프링 부트가 해줌

    @GetMapping("/articles/new")
    public String newArticleForm() {

        return "articles/new";
    }

    @PostMapping("/articles/create")    // form 태그에서 method를 post로 지정했기 때문에 PostMapping 사용
    public String CreateArticle(ArticleForm form){      // 받은 데이터를 ArticleForm의 form이라는 파라미터로 보내진다.
        System.out.println(form.toString());    // ArticleForm클래스의 toString 메서드로 데이터가 잘 받아졌는지 확인한다.

        // 1. Dto를 Entity로 변환한다.
        Article/* Article이라는 Entity클래스 */ article = form.toEntity(); // toEntity 메서드는 Dto에 있는 메서드
        System.out.println(article.toString());

        // 2. Repository에게 Entity를 DB에 저장하게 한다.
        Article saved = articleRepository.save(article);    // save(): CrudRepository에서 기본적으로 사용하게 해주는 메서드중 하나
        System.out.println(saved.toString());
        return "";
    }


}
