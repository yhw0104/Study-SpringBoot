package com.example.springstudy.api;

import com.example.springstudy.entity.Article;
import com.example.springstudy.dto.ArticleForm;
import com.example.springstudy.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@Slf4j
public class ArticleApiController {

    @Autowired  // DI 외부에서 가져온다. (의존관계 주입)
    private ArticleRepository articleRepository;

    // GET

    // 게시글 페이지 (메인 페이지)
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll(); 
    }

    // 게시글 상세 페이지
    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // POST (게시글 추가)
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {   // @RequestBody -> JSON 데이터 받기
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // PATCH (게시글 수정)
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> edit(@PathVariable Long id, @RequestBody ArticleForm dto){  // Article을 담아서 ResponseEntity로 리턴 값을 보내야 한다. 그래야 응답 코드를 반환할 수 있다.
    // ResponseEntity에 Article이 담겨서 JSON으로 반환이 된다.

        // 1. 수정용 Entity 생성
        Article article = dto.toEntity();

        // 2. 대상 Entity 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상이 없거나, id가 없는경우)
        if( target != null || id != target.getId()) {
            // 400 요청, 잘못된 응답 요청!
            log.info("잘못된 응답 요청! id: {}, articles: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);   // BAD_REQUEST = 400번 코드 반환
        }

        // 4. 업데이트 및 정상 응답(200)
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        // 대상 Entity 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 대상 Entity가 없다면 오류 코드 반환
        if(target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);   // BAD_REQUEST = 400번 코드 반환
        }

        // 데이터 삭제 및 반환
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
