package com.example.springstudy.service;

import com.example.springstudy.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅 된다.
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test   // 해당 메서드가 테스트를 위한 코드라는 것을 선언하는 어노테이션
    void index() {
        // 예상 시나리오
        Article a = new Article(1L, "1111", "가가가가");
        Article b = new Article(1L, "2222", "나나나나");
        Article c = new Article(1L, "3333", "다다다다");
        List<Article> expacted = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 실제 결과
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expacted.toString(), articles.toString());

    }
}