package com.ohgiraffers.springproject.hy.controller;

import com.ohgiraffers.springproject.hy.model.dto.HyMovieDTO;
import com.ohgiraffers.springproject.hy.service.HyMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

        // GPT 가 여기서부터 try catch 써서 예외처리 하라고 하는데 try catch 어떻게 들어가지ㅠㅠ
        // ▽ GPT 가 만들어준 try catch 로 리다이렉트랑 메세지, 예외처리 로직
        try {
            //▽▽▽ savepost가 두번 호출되어서 두번 저장되는거였다ㅠㅠ 휴..
            //hyMovieService.savepost(hyMovieDTO);
            mv.setViewName("redirect:/");
            mv.addObject("message", "Post saved successfully");
        } catch (Exception e) {
            mv.setViewName("error");
            mv.addObject("message", "Failed to save post");
        }
        // ▽ ModelAndView 객체를 반환
        return mv;
        // 리턴..레파지토리를 거쳐 다시 서비스로 가나?
    }



 //은진님주석// [전체조회] && [메인화면]
    //전체 조회할 화면을 가져온다.
    // 1. DB 에 저장된 데이터는 title 과 content이다
    // 2. 서비스 클래스에서 조회할 데이터를 (DTO)타입으로 가져온다.
    // 3. 근데 여러개니까 List<BlogDTO> 타입으로 가져온다.
    // 4. postList 라는 키를 통해서 blogDTOList 데이터를 전달해준다.
    // 가져올 때 엔티티에 담긴 데이터를 DTO로 가져오나? 응 맞더라
    // 5. list의 view 를 반환한다.
    @GetMapping("/")
    public ModelAndView list(ModelAndView list){
        List<HyMovieDTO> hyMovieDTOList = hyMovieService.getAllpost();
        list.addObject("postList", hyMovieDTOList);
        // postList 는 html 과 연결되어 있음 // 대소문자 신경쓰기!!

        list.setViewName("list");
        // index 로 두고 기본 템플릿으로 열었다면 코드 리소스를 줄일 수 있다.
        return list;
    }


    // [상세조회]
    //get 요청은 주로 데이터를 조회할 때 사용
    @GetMapping("/posts/{id}") //url경로. {} 안에 있는 id 는 경로변수. 실제 url에서 값을 받아옴
    public ModelAndView detail(@PathVariable("id") Integer id, Model model){
        // △ ModelAndView 는 반환타입
        // △ @PathVariable 은 경로변수 url에서 id를 가져와 Integer id 변수에 할당.//("")를 사용해 명시적으로 지정할 수 있다.
        // Model은 데이터를 뷰로 전달하는데 사용되는 인터페이스이다.
        HyMovieDTO hyMovieDTO = hyMovieService.getMoviePost(id);
        // △ DTO는 서비스에서 데이터를 받아 컨트롤러로 전달하기 위함
        // △ 서비스 객체의 getMoviePost 메서드를 호출하여 id에 해당하는 게시물의 정보를 가져온다.
        // △ 가져온 정보를 DTO에 담아 저장한다.
        model.addAttribute("post", hyMovieDTO);
        // △ model 객체에 데이터를 추가한다. 이 데이터를 뷰로 전달하면 화면에 표시할수있게됨.
        // △ 뷰에서 사용할 데이터의 이름을 "post"로 한다. //△ DTO는 뷰로 전달할 실제 데이터.

        // ▽ 호출자에게 값을 반환.
        // ▽ 반환타입 객체를 생성하고, 뷰 이름이 "detail"인 페이지에 렌더링하겠다
        return new ModelAndView("detail");
    }

    //     [수정하기]
    //     수정할 화면을 가져온다(조회와 같음)
    //get 요청은 주로 데이터를 조회할 때 사용//여기서는 어떤 id를 수정할지 조회
    @GetMapping("/posts/modify/{id}")
    public ModelAndView modify(@PathVariable Integer id, ModelAndView mv){
        // △ ModelAndView 는 반환타입
        // △ @PathVariable 은 경로변수 url에서 id를 가져와 Integer id 변수에 할당.//("")를 사용해 명시적으로 지정할 수 있다.
        // Model은 데이터를 뷰로 전달하는데 사용되는 인터페이스이다.
        HyMovieDTO hyMovieDTO = hyMovieService.getMoviePost(id);
        // △ DTO는 서비스에서 데이터를 받아 컨트롤러로 전달하기 위함
        // △ 서비스 객체의 getMoviePost 메서드를 호출하여 id에 해당하는 게시물의 정보를 가져온다.
        // △ 가져온 정보를 DTO에 담아 저장한다.
        mv.addObject("post", hyMovieDTO);
        // △ model 객체에 데이터를 추가한다. 이 데이터를 뷰로 전달하면 화면에 표시할수있게됨.
        // △ 뷰에서 사용할 데이터의 이름을 "post"로 한다. //△ DTO는 뷰로 전달할 실제 데이터.
        mv.setViewName("modify");
        // ▽ 반환타입 객체를 생성하고, 뷰 이름이 "modify"인 페이지에 렌더링하겠다
        return mv;
        // 수정 로직을 실제 실행하기전에 어떤 포스트를 수정할지 선택해 가져오는 메서드로
        // 코드는 조회와 같지만 modify 페이지에 랜더링해서 그다음 수정로직을 진행한다.
    }

    @PostMapping("/posts/modify/{id}")
    // ▽ 모델앤뷰는 반환타입. update는 메서드이름.
    public ModelAndView update(@PathVariable Integer id,
                               //△ @패스베리어블은 id를 경로변수url에서 가져와 인티저id에 담아주는거.
                               @ModelAttribute HyMovieDTO hyMovieDTO, ModelAndView mv){
                               //△ @모델어트리뷰트는 뭐지? DTO 객체를 생성?, 모델앤뷰를 mv로 변수명지정.
        //▽ DTO를 modifyPost에 담겠다? 서비스의 getMoviePost 메서드를 호출/(id)값의 포스트를 가져옴.
        HyMovieDTO modifyPost = hyMovieService.getMoviePost(id);
        if(modifyPost != null){ //modifyPost가 비어있는 null값이 아닐 경우.
            //▽ modifyPost에 담겨있을 정보들
            modifyPost.setTitle(hyMovieDTO.getTitle());
            modifyPost.setImageUrl(hyMovieDTO.getImageUrl());
            modifyPost.setBestMent(hyMovieDTO.getBestMent());
            modifyPost.setReviewComment(hyMovieDTO.getReviewComment());

            //▽ 서비스의 updatePost 메서드가 호출되어 modifyPost 데이터를 저장
            hyMovieService.updatePost(modifyPost);
        }
        //▽ 리다이렉트 기능은 알겠는데 왜 여기서 이게 나오는지는 모르겠음;
        mv.setViewName("redirect:/posts/" + id);
        return mv;
    }



    /* 삭제하기 */
    @PostMapping("/posts/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id, ModelAndView mv){
        hyMovieService.deletePost(id);
        mv.setViewName("redirect:/");
        return mv;
    }







}
