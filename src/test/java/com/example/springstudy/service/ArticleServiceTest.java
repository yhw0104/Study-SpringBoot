package com.example.springstudy.service;

import com.example.springstudy.dto.ArticleForm;
import com.example.springstudy.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅 된다.
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test   // 해당 메서드가 테스트를 위한 코드라는 것을 선언하는 어노테이션
    void index() {      // 성공과 실패의 경우를 한가지 테스트에서 실행
        // 예상 시나리오
        Article a = new Article(1L, "1111", "aaaa");
        Article b = new Article(2L, "2222", "bbbb");
        Article c = new Article(3L, "3333", "cccc");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 실제 결과
        List<Article> articles = articleService.index();

        // 비교 (예상 시나리오와 실제 결과가 같은지 비교한다)
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success__존재하는_id_입력() {       // 성공하는 경우는 많기 때문에 여러가지로 나눌 수 있다.( 이 테스트는 id 가 존재했을 때 출력이 잘되는지 확인하는 테스트)
        //예상
        Long id = 1L;
        Article expected = new Article(id, "1111", "aaaa");

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_fail__존재하지_않는_id_입력(){    // 이 테스트는 id가 존재 하지 않을 때 null값이 잘 나오는지 확인하는 테스트
        //예상
        Long id = -1L;
        Article expected = null;    // 없는 id 일 경우 orElse함수를 통해 null값을 반환하기로 했었기 때문에 null을 넣음

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected, article);    // null값은 toString 메서드 사용 X
    }

    @Test
    @Transactional
    void save__성공__title과_content만_있는_dto_입력() {
        //예상
        String title = "4444";
        String content = "dddd";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        // 실제
        Article article = articleService.save(dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void save__실패__id가_포함된_dto_입력() {   // 데이터 작성할때 다양한 실패가 있다. ex) id가 포함된 dto, 다른 변수가 생성된 dto등 -> 그 중 하나를 만드는 것
        //예상
        Long id = 5L;
        String title = "4444";
        String content = "dddd";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;

        // 실제
        Article article = articleService.save(dto); // id가 존재 할 때 null값 반환 (save메서드 보면 알수 있음)

        // 비교
        assertEquals(expected, article);
    }

    // 이 테스트들을 한번에 돌리게 되면 index() 메서드의 오류가 생긴다. (id 4의 데이터가 생성되어서 -> save성공 및 실패 메서드에서 데이터가 추가되었기 때문에)
    // 이 오류는 save메서드 테스트를 트랜잭션(롤백)을 하지 않아서 이다.
    // --> save 성공 및 실패 메서드에 트랜잭션 어노테이션을 작성한다.

    @Test
    @Transactional
    void edit__성공__존재하는_id_와_title만_있는_dto_입력() {
    }

    @Test
    @Transactional
    void edit__실패__존재하지않는_id의_dto_입력(){
    }

    @Test
    @Transactional
    void edit__실패__id만_있는_dto_입력(){
    }

    @Test
    @Transactional
    void delete__성공__존재하는_id_입력() {
    }

    @Test
    @Transactional
    void delete__실패__존재하지_않는_id_입력() {
    }
}