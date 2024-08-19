package com.ohgiraffers.springcrud.controller;

import com.ohgiraffers.springcrud.dao.HYStudyRepository;
import com.ohgiraffers.springcrud.model.dto.HYStudyDTO;
import com.ohgiraffers.springcrud.model.entity.HYStudyEntity;
import com.ohgiraffers.springcrud.service.HYStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;



@Controller //사용자의 요청을 컨트롤하겠다는 어노테이션
@RequestMapping("/") //URL 패턴을 매핑하여 어떤 요청이 특정 메소드나 컨트롤러에 의해 처리될지를 정의
//기본적으로 루트 URL("/")로 들어오는 모든 요청을 해당 핸들러 메소드나 컨트롤러로 매핑
public class HYStudyController {

    // MVC 패턴에 따라 service 클래스를 선언한다
    private HYStudyService hyStudyService;

    @Autowired // 자동주입 어노테이션 / Service 인스턴스를 자동으로 주입
    public HYStudyController(HYStudyService hyStudyService) {
        //생성자를 통해 서비스 인스터스를 저장
        this.hyStudyService = hyStudyService;
    }

    /* [view 페이지] */
    @GetMapping("/")
    public String viewMain(){
        return "viewMain";
    }

    /* [등록하기] */
    // 글을 등록하기 위한 화면은 posts.html
    @GetMapping("/posts")
    public String posts(){
        // 등록하는 html만 가져오는 메소드
        // 모델앤뷰를 안쓰고 리턴이 필요없는 void 사용.
        // ㄴ이유: 1. 특정 로직이 없고, 단순히 해당 URL에 대한 뷰를 렌더링할 때 유용
        //        2. 아무런 데이터를 뷰에 전달할 필요가 없을 때 사용
        //        3. 단순한 페이지를 반환하는 경우 유용
        //        4. void를 반환할 경우, 스프링은 요청 URL을 기반으로 뷰 이름을 추론.
        //           예를 들어, /posts 요청에 대해 posts라는 이름의 뷰를 찾는다.

        // *but, void 사용하면 아묻따 실행될 수 있으니 타입지정 해주는게 좋다.
        return "posts";
    }
//
//    // 작성한 글을 저장 : save
//    // @PostMapping : 데이터를 서버로 전송하거나 리소스를 생성할 때 사용하는 어노테이션
//    @PostMapping("/posts") // 경로는 위에서 불러온 html 경로 그대로.
//    // @ModelAttribute : 스프링 MVC에서 모델 데이터를 컨트롤러에 전달하거나, 모델 데이터를 초기화하는 데 사용되는 강력한 도구.
//    //                   메소드 매개변수에 사용하여 요청 파라미터를 객체에 바인딩하고, 해당 객체를 모델에 추가한다. 뷰에서 사용할 수 있도록.
//    public String posts(@ModelAttribute HYStudyDTO hyStudyDTO, RedirectAttributes value){
//    //                                        △ 데이터를 DTO 객체에 바인딩
//        //▽ 검증.
//        if(hyStudyDTO.getTitle() == null || hyStudyDTO.getTitle().isEmpty()){
//            value.addFlashAttribute("message", "제목은 필수항목입니다.");
//            return "redirect:/posts";
//        }
//        if(hyStudyDTO.getContents() == null || hyStudyDTO.getContents().isEmpty()){
//            value.addFlashAttribute("message", "내용은 필수항목입니다.");
//            return "redirect:/posts";
//        }
//        //▽ 서비스 계층의 savepost 메서드가 호출되어 데이터를 저장//없으면 생성.
//        hyStudyService.savepost(hyStudyDTO);
//        value.addAttribute("등록성공");
//        //▽ 응답할 뷰의 이름을 지정.(html과 같게)
////        mv.setViewName("/postList");
//        return "redirect:/postList";
//
//    }
//
//    /* [전체조회] */
//    // 등록된거 확인
//    @GetMapping("/postList")
//    public String postList(Model model){
//        //▽ 서비스 클래스에서 조회 함수를 불러온다. .viewAllposts()
//        //▽ 여러개니까 List<BlogDTO> 타입으로 가져온다.
//        //▽ 여러개담을 List이름을 설정해준다.
//        List<HYStudyDTO> hyStudyDTOList = hyStudyService.viewAllposts();
//        //▽ postList 라는 키를 통해서 hyStudyDTOList 데이터를 전달해준다.
//        //▽ 엔티티로 데이터베이스에 담았던 것을 DTO로 다시 가져옴.
//        model.addAttribute("postList", hyStudyDTOList);
//        //△ attributeName: "" <- html 내부와 연결되어 있음. 이름을 같게.
//
//        //▽ 응답할 뷰의 이름을 지정.(html과 같게)
//        //▽ 이 리턴 값이 templates 폴더의 postList.html 파일을 가리킴
//        return "postList";
//    }
//
//    /* [상세조회] */
//    // 포스트 클릭하면 내용 콘텐츠 확인하기
//    //▽ user가 클릭한 포스트 경로 /posts/{id}를 받는다
//    @GetMapping("/posts/{id}")
//    public ModelAndView postView(@PathVariable("id") Long id, ModelAndView mv) {
//        // △ @PathVariable 은 받은 경로의 id를 Long id 에 할당.//("")를 사용해 명시적으로 id를 찾으라고 지정할 수 있다.
//        // Model은 데이터를 뷰로 전달하는데 사용할 객체(데이터를 곁들인)
//        HYStudyDTO hyStudyDTO;
//        hyStudyDTO = hyStudyService.postView(id);
//        //△ 서비스의 함수를 호출해서 DTO의 데이터를 찾을 것이다.
//        //△ postView 메서드를 호출. -> (id)에 해당하는 게시물의 정보를 가져온다.
//        //△ 가져온 정보를 매개변수 DTO에 담아 저장
//
//        //if로 예외처리.(내용 데이터가 없는 경우//제목은 클릭해야하니까 존재한다는 전제로?)
//        if (hyStudyDTO.getContents() == null || hyStudyDTO.getContents().isEmpty()) {
//            mv.setViewName("/404");
//            return mv;
//        }
//        mv.addObject("post", hyStudyDTO);
//        //△ model 객체에 데이터를 추가한다. 이 데이터를 뷰로 전달하면 화면에 표시할 수 있음.
//        //△ 뷰에서 사용할 데이터의 이름을 "" 설정. //△ DTO는 뷰로 전달할 실제 데이터를 들고 뛰는 배민1.
//
//        //▽ 반환타입을 리턴
//        mv.setViewName("/postView");
//        return mv;
//    }
//
//    /* [수정하기] */
//    // 수정할 포스트를 먼저 가져온다.
//    @GetMapping("/posts/postUpdate/{id}")
//    public String postEdit(@PathVariable("id") Long id, Model model) {
//        // 호출하는 함수가 자료형타입이 같아야함. 포스트를 먼저 가져오기때문에 상세조회와 거의 같다.
//
//        //▽ 서비스에서 postView함수를 호출하여 id에 해당하는 포스트를 찾아 update에 담아준다.
//        //▽ 찾은 포스트id를 옮겨줄 새 DTO객체를 생성한다
//        HYStudyDTO updateDTO = hyStudyService.postView(id);
//
//        //▽ DTO에 담아준 데이터를 ""어트리뷰트 네임을 키로 하여 모델에 담아 view에 전달한다?
//        model.addAttribute("post", updateDTO);
//
//        // model이 전달을 수행할 view이름. //수정을 작성할 곳으로 보낸다
//        return "/postUpdate";
//    }
//
//    @PostMapping("/posts/postUpdate/{id}")
//    //▽ @PathVariable로 id를 받아오고, @ModeAttribute로 데이터 받을 DTO객체 생성 및 받아옴, Model은 뷰로 전달할 데이터를 담는 그릇
//    public String postUpdate(@PathVariable("id") Long id, @ModelAttribute HYStudyDTO hyStudyDTO, RedirectAttributes value) {
//        //▽ 서비스에서 어떤 포스트인지 id로 찾아주고 새로선언한 DTO에 담아줌
//        // ▽ 서비스의 postUpdate 메서드가 호출되어 DTO가 데이터 전달 및 업데이트(수정)//서비스로 go!
//        HYStudyDTO updateDTO = hyStudyService.postUpdate(id, hyStudyDTO);
//
//        //▽ DTO가 데이터를 담고있는지 확인. null이 아니면 if함수 실행
//        //▽ 검증.
//        if(updateDTO.getTitle() == null || updateDTO.getTitle().isEmpty()){
//            value.addFlashAttribute("message", "제목은 필수항목입니다.");
//            return "redirect:/posts/postUpdate/{id}";
//        }
//        if(updateDTO.getContents() == null || updateDTO.getContents().isEmpty()){
//            value.addFlashAttribute("message", "내용은 필수항목입니다.");
//            return "redirect:/posts/postUpdate/{id}";
//        }
//
//        // ▽ 수정을 완료했으므로 수정된 화면을 보여줌.
//        return "redirect:/posts/" + updateDTO.getId();
//        // spring > 페이지의 이름으로 인식함.수정된 데이터를 가지고 있는 DTO와 해당getId로 포스트지정.
//    }
//
//    /* [삭제하기] */
//    @GetMapping("/posts/postDelete/{id}")
//    public ModelAndView postDelete(@PathVariable("id") Long id, ModelAndView mv){
//        HYStudyDTO deleteDTO = hyStudyService.postView(id);
//        mv.addObject("post", deleteDTO);
//        mv.setViewName("/postDelete");
////        hyStudyService.deletePost(id);
//        return mv;
//    }
//
//    @PostMapping("/posts/postDelete/{id}")
//    public String postDelete(@PathVariable("id") Long id, RedirectAttributes value){
//        HYStudyDTO deleteDTO = hyStudyService.postView(id);
//        if(deleteDTO != null){
//            hyStudyService.deletePost(id);
//            value.addAttribute("삭제성공");
//            return "redirect:/postList";
//        }else {
//            value.addAttribute("삭제실패");
//            return "redirect:/posts/" + deleteDTO.getId();
//        }
//
//    }




/* [상세조회 - 예외처리 try-catch + throw exception 구문 ] */
    /*//▽ try-catch는 예외처리를 위함(데이터가 없는 경우)
        try {
        //▽ tr-catch문에서  throw로 예외를 발생시킬 수 있다.
        *//* throw new Exception("예외를 강제로 발생시켰습니다."); *//*
        HYStudyDTO hyStudyDTO = hyStudyService.postView(id);
        //△ 서비스의 함수를 호출해서 DTO의 데이터를 찾을 것이다.
        //△ postView 메서드를 호출. -> (id)에 해당하는 게시물의 정보를 가져온다.
        //△ 가져온 정보를 매개변수 DTO에 담아 저장
        mv.addObject("post", hyStudyDTO);
        //△ model 객체에 데이터를 추가한다. 이 데이터를 뷰로 전달하면 화면에 표시할 수 있음.
        //△ 뷰에서 사용할 데이터의 이름을 "" 설정. //△ DTO는 뷰로 전달할 실제 데이터를 들고 뛰는 배민1.
        mv.setViewName("postView");

        //▽ 예외처리.(데이터가 없는 경우)//이거 어떻게 확인하지??? -> throw new Exception("예외 강제발생");
    }catch (Exception e){
        //△ Exception은 = '예외' e 라는 매개변수 선언.
        //▽ 매개변수 e에 .getMessage()로 에러메세지를 가져옴. 이것을 "errorMessage"로 명명하여 mv.에 담아줌
        mv.addObject("errorMessage", e.getMessage());
        //▽ mv에 담은 것을 어느 view에 보여줄지 뷰 이름으로 설정해줌
        mv.setViewName("/404"); // 404 에러 페이지로 설정
    }*/




}
