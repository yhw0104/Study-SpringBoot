package com.example.springstudy.api;

import com.example.springstudy.entity.Article;
import com.example.springstudy.dto.ArticleForm;
import com.example.springstudy.repository.ArticleRepository;
import com.example.springstudy.service.ArticleService;
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
    private ArticleService articleService;

    // GET

    // 게시글 페이지 (메인 페이지)
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    // 게시글 상세 페이지
    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id) {
        return articleService.show(id);
    }

    // POST (게시글 추가)
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {   // @RequestBody -> JSON 데이터 받기
        Article created = articleService.save(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH (게시글 수정)
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> edit(@PathVariable Long id, @RequestBody ArticleForm dto){  // Article을 담아서 ResponseEntity로 리턴 값을 보내야 한다. 그래야 응답 코드를 반환할 수 있다.
    // ResponseEntity에 Article이 담겨서 JSON으로 반환이 된다.

        Article updated = articleService.edit(id, dto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //트랜잭션 연습
    // 트랜잭션 -> 실패 -> 롤백
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
