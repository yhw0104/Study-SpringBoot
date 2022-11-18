package com.example.springstudy.dto;

import com.example.springstudy.entity.Article;

public class ArticleForm {

    private String title;   // 제목
    private String content; // 내용

    //생성자
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {      //데이터가 잘 받아졌는지 확인하기 위한 메서드
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {

        return new Article(null, title, content);   // Entity 클래스의 생성자를 리턴 값으로 갖는다.
    }
}
