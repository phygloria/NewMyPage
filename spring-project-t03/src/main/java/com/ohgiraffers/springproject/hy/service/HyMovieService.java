package com.ohgiraffers.springproject.hy.service;

import com.ohgiraffers.springproject.hy.model.dto.HyMovieDTO;
import com.ohgiraffers.springproject.hy.model.entity.HyMovieEntity;
import com.ohgiraffers.springproject.hy.repository.HyMovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

@Service
public class HyMovieService {

    private final HyMovieRepository hyMovieRepository;

    @Autowired
    public HyMovieService(HyMovieRepository hyMovieRepository){
        this.hyMovieRepository = hyMovieRepository;
    }

    public void savepost(HyMovieDTO hyMovieDTO) {
        HyMovieEntity hyMovieEntity = new HyMovieEntity();
        hyMovieEntity.setReviewTitle(hyMovieDTO.getReviewTitle());
        hyMovieEntity.setReviewDetail(hyMovieDTO.getReviewDetail());

        hyMovieRepository.save(hyMovieEntity);
    }

    @Transactional
    public List<HyMovieDTO> getMovies() {
        List<HyMovieEntity> hyMovieEntities = hyMovieRepository.findAll();
        List<HyMovieDTO> hyMovieDTOList = new ArrayList<>();
        for (HyMovieEntity hyMovieEntity : hyMovieEntities) {
            HyMovieDTO hyMovieDTO = new HyMovieDTO();
            hyMovieDTO.setReviewTitle(hyMovieEntity.getReviewTitle());
            hyMovieDTO.setReviewDetail(hyMovieEntity.getReviewDetail());
            hyMovieDTOList.add(hyMovieDTO);
        }
        return hyMovieDTOList;
    }

    public HyMovieDTO getMovieById(Long id) {
        HyMovieEntity hyMovieEntity = hyMovieRepository.findById(id).get();
        HyMovieDTO hyMovieDTO = new HyMovieDTO();
        hyMovieDTO.setReviewTitle(hyMovieEntity.getReviewTitle());
        hyMovieDTO.setReviewDetail(hyMovieEntity.getReviewDetail());
        return hyMovieDTO;
    }



    /*public int post(HyMovieDTO hyMovieDTO){
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
    }*/
}