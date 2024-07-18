package com.ohgiraffers.springcrud.model.dto;

import com.ohgiraffers.springcrud.model.entity.HYStudyEntity;

import javax.swing.*;

public class HYStudyDTO {

    private Long id;
    private String title;
    private String contents;

    public HYStudyDTO() {
    }

    public HYStudyDTO(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public HYStudyDTO(HYStudyEntity hyStudyEntity) {

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
        return "HYStudyDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
