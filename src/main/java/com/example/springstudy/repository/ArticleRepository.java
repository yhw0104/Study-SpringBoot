package com.example.springstudy.repository;

import com.example.springstudy.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository <Article, Long> {
    //관리대상 entity, 관리대상 entity의 대표값 타입을 파라미터에 넣어줘야된다.
    // CrudRepository를 상속 받음으로서 CRUD의 기본동작을 추가 코드 구현 없이 사용을 가능하게 한다.
}
