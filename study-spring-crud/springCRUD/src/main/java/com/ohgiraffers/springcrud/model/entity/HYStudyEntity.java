package com.ohgiraffers.springcrud.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "study")// 데이터베이스에 매핑될 테이블 이름
public class HYStudyEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 기본키 생성
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "contents")
    private String contents;

    public HYStudyEntity() {
    }

    public HYStudyEntity(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", title=" + title +
                ", contents=" + contents +
                '}';
    }
}
