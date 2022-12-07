package com.example.springstudy.repository;

import com.example.springstudy.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {   //JpaRepository<>안에 관리할 대상, 그리고 그 대상의 FK의 타입을 설정하면 된다.
    // 특정 게시글의 모든 댓글 조회 (JpaRepository에 없는 메서드 작성)
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true)
    List<Comment> findByArticleId(Long articleId); // 해당 파라미터가 @Query value문의 articleId와 이름이 같아야한다.

    // 특정 닉네임의 모든 댓글 조회
    List<Comment>findByNickname(String nickname);
}
