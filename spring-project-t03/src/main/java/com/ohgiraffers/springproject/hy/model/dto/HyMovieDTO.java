package com.ohgiraffers.springproject.hy.model.dto;

public class HyMovieDTO {

    private Integer id;
    private String title;
    private String imageUrl;
    private String bestMent;
    private String reviewComment;

    public HyMovieDTO() {
    }

    public HyMovieDTO(Integer id, String title, String imageUrl, String bestMent, String reviewComment) {
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
        return "HyMovieDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", bestMent='" + bestMent + '\'' +
                ", reviewComment='" + reviewComment + '\'' +
                '}';
    }
}
