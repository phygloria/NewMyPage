package com.ohgiraffers.springproject.hy.model.dto;

public class HyMovieDTO {

    private String title;
    private String imageUrl;
    private String bestMent;
    private String director;
    private String produce;
    private String actor;
    private String genre;
    private String releaseDate;
    private String storyTitle;
    private String storyText;
    private String reviewTitle;
    private String reviewDetail;

    public HyMovieDTO() {
    }

    public HyMovieDTO(String title, String imageUrl, String bestMent, String director, String produce, String actor, String genre, String releaseDate, String storyTitle, String storyText, String reviewTitle, String reviewDetail) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.bestMent = bestMent;
        this.director = director;
        this.produce = produce;
        this.actor = actor;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.storyTitle = storyTitle;
        this.storyText = storyText;
        this.reviewTitle = reviewTitle;
        this.reviewDetail = reviewDetail;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getStoryText() {
        return storyText;
    }

    public void setStoryText(String storyText) {
        this.storyText = storyText;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewDetail() {
        return reviewDetail;
    }

    public void setReviewDetail(String reviewDetail) {
        this.reviewDetail = reviewDetail;
    }

    @Override
    public String toString() {
        return "HyMovieDTO{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", bestMent='" + bestMent + '\'' +
                ", director='" + director + '\'' +
                ", produce='" + produce + '\'' +
                ", actor='" + actor + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", storyTitle='" + storyTitle + '\'' +
                ", storyText='" + storyText + '\'' +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", reviewDetail='" + reviewDetail + '\'' +
                '}';
    }
}
