package com.ohgiraffers.springproject.hy.controller;

import com.ohgiraffers.springproject.hy.model.dto.HyMovieDTO;
import com.ohgiraffers.springproject.hy.service.HyMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //사용자의 요청을 컨트롤하겠다는 어노테이션
public class HyController {

    // MVC 패턴에 따라 service 클래스만 선언한다 /서비스 클래스는 데이터를 처리함
    private final HyMovieService hyMovieService;

    // 자동주입 어노테이션 / Service 인스턴스를 자동으로 주입
    @Autowired
    public HyController(HyMovieService hyMovieService){
        //생성자를 통해 서비스 인스터스를 저장
        this.hyMovieService = hyMovieService;
    }

    // 루트 URL에 대한 GET 요청을 처리하기 위해 사용되는 어노테이션
    // 클라이언트가 http://localhost:8080/에 GET 요청을 보낼 때 이 메서드가 호출됩니다.
    @GetMapping("/")
    public String haYoungPage() {
        // 경로설정
        return "hayoung/Hayoung";
    }

    //[등록하기]
    // 글쓰기(등록) 화면을 보여준다.
    // 글을 등록하기 위한 화면은 posts.html 이다.
    @GetMapping("/posts")
    public void posts(){
        // 게시글의 제목, 내용을 입력하는 화면
        // 모델앤뷰가 필요없으니 등록하면 등록하는 html만 가져오면 된다.
    }

    // 등록한 글을 보낸다. 데이터에 저장한다.
    // @PostMapping : 데이터를 서버로 전송하거나 리소스를 생성할 때 사용하는 어노테이션
    @PostMapping("/posts")
    // ▽ HyMovieDTO 객체를 받아 저장한 후, 뷰 이름을 "posts"로 설정하여 응답하겠다.
    public ModelAndView posts(@ModelAttribute HyMovieDTO hyMovieDTO, ModelAndView mv){
                                            // △ 요청 데이터를 HyMovieDTO 객체에 바인딩
        // ▽ 서비스 계층의 savepost 메서드가 호출되어 데이터를 저장
        hyMovieService.savepost(hyMovieDTO);
        // ▽ 응답할 뷰의 이름을 "posts"로 설정
        mv.setViewName("posts");

        /* 리다이렉트 로직 */
        // ▽ 수업자료 보고 만든 리다이렉트 로직
        if(hyMovieDTO.getTitle() == null || hyMovieDTO.getTitle().equals("")){
            mv.setViewName("redirect:/hyounginput");
        }
        if(hyMovieDTO.getImageUrl() == null || hyMovieDTO.getImageUrl().equals("")){
            mv.setViewName("redirect:/hyounginput");
        }
        if(hyMovieDTO.getReviewTitle() == null || hyMovieDTO.getReviewTitle().equals("")){
            mv.setViewName("redirect:/hyounginput");
        }

        // GPT 가 여기서부터 try catch 써서 예외처리 하라고 하는데 try catch 어떻게 들어가지ㅠㅠ
        // ▽ GPT 가 만들어준 try catch 로 리다이렉트랑 메세지, 예외처리 로직
        try {
            hyMovieService.savepost(hyMovieDTO);
            mv.setViewName("redirect:/posts");
            mv.addObject("message", "Post saved successfully");
        } catch (Exception e) {
            mv.setViewName("error");
            mv.addObject("message", "Failed to save post");
        }


        // ▽ ModelAndView 객체를 반환하여 클라이언트에게 응답
        return mv;
    }


    /*@PostMapping("/posts")
    public ModelAndView posts(HyMovieDTO hyMovieDTO, ModelAndView mv){
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
    }*/

}
