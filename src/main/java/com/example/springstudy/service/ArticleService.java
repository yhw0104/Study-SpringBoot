package com.example.springstudy.service;

import com.example.springstudy.dto.ArticleForm;
import com.example.springstudy.entity.Article;
import com.example.springstudy.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service    // 서비스 선언(서비스 객체를 스프링부트에 생성)
public class ArticleService {

    @Autowired  // DI
    private ArticleRepository articleRepository;

    // 게시글 메인 페이지
    public List<Article> index(){
        return articleRepository.findAll();
    }

    // 게시글 상세 페이지
    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    // 게시글 추가
    public Article save(ArticleForm dto){
        Article article = dto.toEntity();
        if(article.getId() != null) {   // id 값이 존재한다면 널값 반환(수정이 아니라 생성이기 때문에 기존 id 값의 데이터가 변경되면 안됨)
            return null;
        }
        return articleRepository.save(article);
    }

//    게시글 수정
//    public Article edit(long id, ArticleForm dto){
//        // 1. 수정용 Entity 생성
//        Article article = dto.toEntity();
//
//        // 2. 대상 Entity 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 3. 업데이트
//        target.patch(article);
//        Article updated = articleRepository.save(target);
//
//        // 4. 업데이트 값 반환
//        return updated;
//    }

//  게시글 수정
    public Article edit(long id, ArticleForm dto){
        // 1. 수정용 Entity 생성
        Article article = dto.toEntity();

        // 2. 대상 Entity 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 업데이트
        if( target != null) {
            target.patch(article);
            Article updated = articleRepository.save(target);
            return updated;
        }
        else return null;
    }


    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public Article delete(Long id){
        // 대상 Entity 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리 (대상이 없는 경우)
        if(target == null){
             return null;
        }
        // 데이터 삭제 및 반환
        articleRepository.delete(target);
        return target;
    }


    // 트랜잭션이란 예외처리 오류가 나면 트랜잭션으로 묶은 부분의 처음으로 다시 되돌아간다.
    // 이 트랜잭션 예제를 보면 talend tester를 통해 데이터 3개를 넣고 난 후 예외를 만들었다. (예외 처리가 된다.)
    // 로그를 보게되면 3개의 데이터가 생성된 것을 확인 할 수 있다.
    // 하지만 예외 값이 나와 DB를 확인 해보면 생성 되었던 3개의 데이터가 없어져 있는 것을 확인 할 수 있다.
    @Transactional  // 해당 메서드를 트랜잭션으로 묶는다!
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 Entity 묶음으로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

//        **위의 내용하고 같은 내용(for문 사용)**
//        List<Article> articleList = new ArrayList<>();
//        for(int i = 0; i < dtos.size(); i++){
//            ArticleForm dto = dtos.get(i);
//            Article entity = dto.toEntity();
//            articleList.add(entity);
//        }

        // Entity 묶음을 DB에 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

//        **위의 내용하고 같은 내용(for문 사용)**
//        for(int i = 0; i <aritcleList.size(); i++){
//            Article article = articleList.get(i);
//            articleRepository.save(article);
//        }

        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(    // id가 -1인 값을 찾을때
                () -> new IllegalArgumentException("결재 실패!")
        );
        // 결과값 반환
        return articleList;
    }
}
