package com.example.springstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// -> 데이터베이스가 알아서 id를 자동으로 생성하게 함(더미데이터가 있어도 자동으로 다음 id로 생성)    // DB가 id를 자동생성해주는 어노테이션(새로운 레코드가 생성될 때마다 자동으로 PK에 +1을 해주는 어노테이션)
    private Long id;

    @ManyToOne // 해당 댓글 엔티티 여러개가, 하나의 Article에 연관된다! (다대일 관계 설정)
    @JoinColumn(name = "article_id") // article_id 컬럼에 Article의 대표값을 저장!
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public void setArticle(Article article) {
        this.article = article;
    }

}
