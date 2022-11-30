package com.example.springstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity // DB가 해당 객체를 Entity로 인식하게 하는 어노테이션 -> 해당 클래스로 테이블 생성 --> create table article() 쿼리문 자동 생성
// DB의 테이블과 일대일로 매칭되는 객체 단위
// spring.jpa.hibernate.ddl-auto 설정이 create 혹은 update로 되어있을 경우 spring 프로젝트가 시작될 때 EntityManager가 자동으로 DDL을 수행해 테이블을 생성함
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {

    @Id     // PK(Primary Key)기본키 지정
    @GeneratedValue (strategy = GenerationType.IDENTITY)    // -> 데이터베이스가 알아서 id를 자동으로 생성하게 함(더미데이터가 있어도 자동으로 다음 id로 생성)    // DB가 id를 자동생성해주는 어노테이션(새로운 레코드가 생성될 때마다 자동으로 PK에 +1을 해주는 어노테이션)
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
