package com.example.springstudy.repository;

import com.example.springstudy.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

// Repository는 입력 받은 데이터들의 처리를 담당한다.
public interface ArticleRepository extends CrudRepository <Article, Long> {
    //관리대상 entity, 관리대상 entity의 대표값 타입을 파라미터에 넣어줘야된다.
    // CrudRepository를 상속 받음으로서 CRUD의 기본동작을 추가 코드 구현 없이 사용을 가능하게 한다.
    @Override
    ArrayList<Article> findAll();
}
