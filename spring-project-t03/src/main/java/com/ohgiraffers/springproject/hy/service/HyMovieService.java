package com.ohgiraffers.springproject.hy.service;

import com.ohgiraffers.springproject.hy.model.dto.HyMovieDTO;
import com.ohgiraffers.springproject.hy.model.entity.HyMovieEntity;
import com.ohgiraffers.springproject.hy.repository.HyMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HyMovieService {

private final HyMovieRepository hyMovieRepository;

    public HyMovieService(HyMovieRepository hyMovieRepository){
        this.hyMovieRepository = hyMovieRepository;
    }

    public int post(HyMovieDTO hyMovieDTO){
        List<HyMovieEntity> hyMovieEntities = hyMovieRepository.findAll();

        for (HyMovieEntity post: hyMovieEntities){
            if(post.getTitle().equals(hyMovieDTO.getTitle())){
                return 0;
            }
        }

        HyMovieEntity savePost = new HyMovieEntity();
        savePost.setTitle(hyMovieDTO.getTitle());
        savePost.setImageUrl(hyMovieDTO.getImageUrl());
        savePost.setReviewTitle(hyMovieDTO.getReviewTitle());
        savePost.setReviewDetail(hyMovieDTO.getReviewDetail());

        HyMovieEntity result = hyMovieRepository.save(savePost);

        int resultValue = 0;

        if(result != null){
            resultValue = 1;
        }

        return resultValue;

    }

}