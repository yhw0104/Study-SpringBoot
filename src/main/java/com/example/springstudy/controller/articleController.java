package com.example.springstudy.controller;

import com.example.springstudy.dto.ArticleForm;
import com.example.springstudy.entity.Article;
import com.example.springstudy.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j  //로깅을 위한 어노테이션 로깅이란 서버에서 일어나는 일들을 다 기록하는 것
public class articleController {

    @Autowired  //스프링부트가 미리 생성해 놓은 객체를 가져다가 연결
    private ArticleRepository articleRepository;    // ArticleRepository객체를 사용하기 위해 선언
                                                    // 객체를 구현할 필요가 없다 -> 스프링 부트가 해줌

    @GetMapping("/articles/new")
    public String newArticleForm() {

        return "articles/new";
    }

    @PostMapping("/articles/create")    // form 태그에서 method를 post로 지정했기 때문에 PostMapping 사용
    public String CreateArticle(ArticleForm form){      // 받은 데이터를 ArticleForm의 form이라는 파라미터로 보내진다. (Dto처리)
        log.info(form.toString());      // -> 로깅기능으로 대체!
        //System.out.println(form.toString());    // ArticleForm클래스의 toString 메서드로 데이터가 잘 받아졌는지 확인한다.

        // 1. Dto를 Entity로 변환한다.
        Article/* Article이라는 Entity클래스 */ article = form.toEntity(); // toEntity 메서드는 Dto에 있는 메서드
        log.info(article.toString());
        //System.out.println(article.toString());

        // 2. Repository에게 Entity를 DB에 저장하게 한다.
        Article saved = articleRepository.save(article);    // save(): CrudRepository에서 기본적으로 사용하게 해주는 메서드중 하나
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")   // Url 입력 받아오기
    public String show(@PathVariable Long id, Model model){  //@PathVariable -> 메서드 인자에 사용되어 URI 템플릿 변수의 값({id})을 메서드 인자로 할당하는데 사용
        // 1. Id로 데이터를 가져옴 -> 데이터를 가져오는 Repository로 데이터를 가져옴
           Article articleEntity = articleRepository.findById(id).orElse(null); // 만약 해당 id가 없다면 null을 할당
        // 2. 가져온 데이터를 모델에 등록
            model.addAttribute("article", articleEntity);   // articleEntity의 값을 article이라는 이름으로 model로 등록한다.
        // 3. 보여줄 페이지를 설정!
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 Article을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);
        // 3. 뷰페이지 설정
        return "articles/index";    //articles/index.mustache
    }
}
