package com.example.springstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // DB가 해당 객체를 Entity로 인식하게 하는 어노테이션
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {

    @Id     // PK(Primary Key)기본키 지정
    @GeneratedValue     // 새로운 레코드가 생성될 때마다 자동으로 PK에 +1을 해주는 어노테이션
    private Long id;

    @Column     // DB 테이블에 연결해 주는 어노테이션
    private String title;

    @Column
    private String content;

    // 생성자 추가
    // public Article(Long id, String title, String content) {
    //    this.id = id;
    //    this.title = title;
    //    this.content = content;
    //}

    //@Override
    //public String toString() {
    //   return "Article{" +
    //            "id=" + id +
    //           ", title='" + title + '\'' +
    //            ", content='" + content + '\'' +
    //           '}';
    //}
}
