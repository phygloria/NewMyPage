package com.ohgiraffers.blog_project.Blog.controller;

import com.ohgiraffers.blog_project.Blog.model.dto.BlogDTO;
import com.ohgiraffers.blog_project.Blog.repository.BlogRepository;
import com.ohgiraffers.blog_project.Blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// 좋아요를 비동기 요청으로 보내는 거
// @RestController : RestAPI  사용
// @RequestMapping : 코드 리소스를 줄인다.


@Controller
public class BlogController {

    // mvc 패턴에 따라 service 클래스만 선언한다
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    //[등록하기]
    // 글을 등록하는 화면 = 글을 쓰는 화면을 보여준다.
    // 글을 등록하기 위한 화면은 posts.html 이다.
    @GetMapping("/posts")
    public void posts() {

    }
    // 게시글의 제목, 내용을 입력하는 화면을 가져와야 한다
    // 모델엔 뷰가 필요없으니 등록하면  등록하는 html만 가져오면 된다.

    // 등록한 글을 보낸다 데이터에 저장한다
    @PostMapping("/posts")
    public ModelAndView posts(@ModelAttribute BlogDTO blogDTO, ModelAndView mv) {
        blogService.savepost(blogDTO);
        mv.setViewName("posts");
        return mv;
    }
    // 1. 사용자가 입력한 데이터를 받는다. html
    // 2. 사용자가 입력한 데이터를 모델에 담는다?
        //  @ModelAttribute 어노테이션을 사용하면 사용자가 폼 요청에서 보낸 데이터를 DTO 객체에 바인딩하게 됨.
        // ModelAndView 는 반환할 뷰의 이름과 모델 데이터를 함께 포함하는 객체다.
        // mv.setViewName("posts")를 통해 뷰 이름을 posts 로 설정한다.
    // 3. 그걸 서비스로 보낸다.



    //    [전체조회]
    //    전체 조회할 화면을 가져온다.
    // 1. DB 에 저장된 데이터는 title 과 content이다
    // 2. 서비스 클래스에서 조회할 데이터를 (DTO)타입으로 가져온다.
    // 3. 근데 여러개니까 List<BlogDTO> 타입으로 가져온다.
    // 4. postList 라는 키를 통해서 blogDTOList 데이터를 전달해준다.
    // 가져올 떄 엔티티에 담긴 데이터를 DTO로 가져오나? 응 맞더라
    // 5. list view 를 반환한다.
    @GetMapping("/")
    public String list(Model model){
        List<BlogDTO> blogDTOList = blogService.getAllpost();
        model.addAttribute("postList", blogDTOList);
        // postList 는 html 과 연결되어 있음
        return "list";
    // index 로 두고 기본 템플릿으로 열었다면 코드 리소스를 줄일 수 있다.
    //
    }

    // [상세조회]
    // 1. 수정할 화면을 가져온다.(상세조회)
    // 2. 리소스 : edit + pathvalue
    // 3. 블로그 서비스에서 getpost 함수를 통해 데이터를 가져오고 dto 에 담는다.
    // 4. detail 이라는 화면에 데이터를 넣어주는데 - 그 떄 "post"라는 키값을 사용한다.
    // 5. 그리고 그 view 를 반환하여 보여준다.
    @GetMapping("/posts/{id}")
    public ModelAndView detail(@PathVariable Integer id, Model model){
        BlogDTO blogDTO =blogService.getpost(id);
        model.addAttribute("post", blogDTO);
        return new ModelAndView("detail");
    }

    //     [수정하기]
    //     수정할 화면을 가져온다
      @GetMapping("/posts/modify/{id}")
    public ModelAndView modify(@PathVariable Integer id, Model model){
        BlogDTO blogDTO = blogService.getpost(id);
        model.addAttribute("post", blogDTO);
        return new ModelAndView("modify");
      }

    //수정된 데이터를 보낸다
     @PostMapping("/posts/modify/{id}")
    public ModelAndView update(@PathVariable Integer id, @ModelAttribute BlogDTO blogDTO, ModelAndView mv) {
        BlogDTO existingPost = blogService.getpost(id);
        if(existingPost != null){
            existingPost.setTitle(blogDTO.getTitle());
            existingPost.setContent(blogDTO.getContent());
            blogService.updatePost(existingPost);
        }
        mv.setViewName("redirect:/posts/" + id);
        return mv;
     }


//    [삭제하기]
//    // 버튼 누르면 해당하는 id 가 삭제된다
        @PostMapping("/posts/delete/{id}")
        public ModelAndView delete(@PathVariable Integer id, ModelAndView mv) {
        blogService.deletePost(id);
        mv.setViewName("redirect:/");
        return mv;
        }





}
