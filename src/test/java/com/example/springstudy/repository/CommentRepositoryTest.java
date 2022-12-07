package com.example.springstudy.repository;

import com.example.springstudy.entity.Article;
import com.example.springstudy.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest // JPA와 연동한 테스트
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test

    @DisplayName("특정 게시글의 모든 댓글 조회") // 테스트 결과에 보여줄 이름(테스트 메서드 이름이 아닌 DisplayName안의 이름으로 보여줌)
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 4L;
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상하기
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ");
            Comment a = new Comment(1L, article, "박재언", "스타 워즈");
            Comment b = new Comment(2L, article, "윤현우", "아이언 맨");
            Comment c = new Comment(3L, article, "양준혁", "어바웃 타임");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글 출력!");
        }
    }

    @Test
    void findByNickname() {
    }
}