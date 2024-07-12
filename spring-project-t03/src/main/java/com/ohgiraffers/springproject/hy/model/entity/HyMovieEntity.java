package com.ohgiraffers.springproject.hy.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "phy")
public class HyMovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //    블로그타이틀
    @Column(name = "title")
    private String title;

    //    포스터
    @Column(name = "image_url")
    private String imageUrl;

    //    명대사
    @Column(name = "best_ment")
    private String bestMent;


    /*//    영화정보  --- info로 묶을 수 있다.
    @Column(name = "director")
    private String director;
    @Column(name = "produce")
    private String produce;
    @Column(name = "actor")
    private String actor;
    @Column(name = "genre")
    private String genre;
    @Column(name = "release_date")
    private String releaseDate;*/


/*    //    리뷰  --- 리뷰로 묶을 수 있다.
    @Column(name = "review_title")
    private String reviewTitle;
    @Column(name = "review_detail")
    private String reviewDetail;*/

    @Column(name = "review_Comment")
    private String reviewComment;

    public HyMovieEntity() {

    }

    public HyMovieEntity(Integer id, String title, String imageUrl, String bestMent, String reviewComment) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.bestMent = bestMent;
        this.reviewComment = reviewComment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBestMent() {
        return bestMent;
    }

    public void setBestMent(String bestMent) {
        this.bestMent = bestMent;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    @Override
    public String toString() {
        return "HyMovieEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", bestMent='" + bestMent + '\'' +
                ", reviewComment='" + reviewComment + '\'' +
                '}';
    }
}
