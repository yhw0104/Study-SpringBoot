package com.example.springstudy.controller;

import com.example.springstudy.dto.ArticleForm;
import com.example.springstudy.entity.Article;
import com.example.springstudy.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j  //로깅을 위한 어노테이션 로깅이란 서버에서 일어나는 일들을 다 기록하는 것
public class articleController {

    @Autowired  //스프링부트가 미리 생성해 놓은 객체를 가져다가 연결
    private ArticleRepository articleRepository;    // ArticleRepository객체를 사용하기 위해 선언
                                                    // 객체를 구현할 필요가 없다 -> 스프링 부트가 해줌

    //게시글 추가 페이지 이동
    @GetMapping("/articles/new")
    public String newArticleForm() {

        return "articles/new";
    }

    // 게시글 추가
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

    // 게시글 상세 페이지
    @GetMapping("/articles/{id}")   // Url 입력 받아오기
    public String show(@PathVariable Long id, Model model){  //@PathVariable -> 메서드 인자에 사용되어 URI 템플릿 변수의 값({id})을 메서드 인자로 할당하는데 사용 / Long id 변수를 url에 있는 id로 사용하고 싶어 사용하는 어노테이션
        // 1. Id로 데이터를 가져옴 -> 데이터를 가져오는 Repository로 데이터를 가져옴
           Article articleEntity = articleRepository.findById(id).orElse(null); // 만약 해당 id가 없다면 null을 할당
        // 2. 가져온 데이터를 모델에 등록
            model.addAttribute("article", articleEntity);   // articleEntity의 값을 article이라는 이름으로 model로 등록한다.
        // 3. 보여줄 페이지를 설정!
        return "articles/show";
    }

    // 게시글 목록 페이지
    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 Article을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);
        // 3. 뷰페이지 설정
        return "articles/index";    //articles/index.mustache
    }

    // 게시글 수정 페이지
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터를 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록
        model.addAttribute("article", articleEntity);

        // 뷰페이지 설정
        return "articles/edit";
    }

    // 게시글 수정 제출
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());      // -> 로깅기능으로 대체! / 콘솔창에서 확인 가능
        //System.out.println(form.toString());    // ArticleForm클래스의 toString 메서드로 데이터가 잘 받아졌는지 확인한다.

        // 1. Dto를 Entity로 변환한다.
        Article articleEntity/* article이라는 Entity클래스 */ = form.toEntity(); // toEntity 메서드는 Dto에 있는 메서드
        log.info(articleEntity.toString());
        //System.out.println(article.toString());

        // 2-1. DB에서 기존 데이터를 가져온다.
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2. 기존 데이터가 있다면 값을 갱신한다.
        if(target != null){
            articleRepository.save(articleEntity);
        }

        // 수정 결과 페이지로 리다이렉트 한다.
        return "redirect:/articles/" + articleEntity.getId();
    }

    // HTML에서 DELETE 요청을 지원하지 않으므로 GetMapping사용
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){    // Long id 변수를 url에 있는 id로 사용하고 싶어 사용하는 어노테이션

        // 1. 삭제 대상을 가져온다
        Article target = articleRepository.findById(id).orElse(null);   // repository안에 있는 메서드 findById 메서드를 사용하여 id를 가져온다.

        // 2. 대상을 삭제한다
        if(target != null){
            articleRepository.delete(target);   // repository안에 있는 메서드 delete 메서드를 사용하여 id를 가져온다. (DB에서 데이터를 가져올 때는 repository에서 가져온다.)
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");      // addFlashAttribute는 일회성이라 한 번 사용되고 없어짐
        }

        // 3. 결과 페이지로 redirect한다
        return "redirect:/articles";
    }
}
