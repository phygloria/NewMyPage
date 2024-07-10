package com.ohgiraffers.springproject.hy.controller;

import com.ohgiraffers.springproject.hy.model.dto.HyMovieDTO;
import com.ohgiraffers.springproject.hy.service.HyMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/page/hayoung")
public class HyController2 {

    private final HyMovieService hyMovieService;

    @Autowired
    public HyController2(HyMovieService hyMovieService){
        this.hyMovieService = hyMovieService;
    }

    @GetMapping("/haYoungPage")
    public String haYoungPage() {
        return "page/hayoung/Hayoung";
    }

    @PostMapping("/post")
    public ModelAndView InputPage(HyMovieDTO hyMovieDTO, ModelAndView mv){
        if(hyMovieDTO.getTitle() == null || hyMovieDTO.getTitle().equals("")){
            mv.setViewName("redirect:/hyounginput");
        }
        if(hyMovieDTO.getImageUrl() == null || hyMovieDTO.getImageUrl().equals("")){
            mv.setViewName("redirect:/hyounginput");
        }
        if(hyMovieDTO.getReviewTitle() == null || hyMovieDTO.getReviewTitle().equals("")){
            mv.setViewName("redirect:/hyounginput");
        }

        int result = hyMovieService.post(hyMovieDTO);

        if(result <= 0){
            mv.setViewName("error/error");
        }else{
            mv.setViewName("hayoung");
        }

        return mv;
    }

}
