package com.ohgiraffers.blog_project.Blog.service;


import com.ohgiraffers.blog_project.Blog.model.dto.BlogDTO;
import com.ohgiraffers.blog_project.Blog.model.entity.BlogEntity;
import com.ohgiraffers.blog_project.Blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    private BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public void savepost(BlogDTO blogDTO) {

        // 검증로직 추가 작성 필요

        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setTitle(blogDTO.getTitle());
        blogEntity.setContents(blogDTO.getContent());

        // 레파지토리를 이용해서 저장
        blogRepository.save(blogEntity);
    }

    // 1. controller에서 전달받은 데이터를 레파지토리로 보내서 저장하는 함수를 만든다.
    // 근데 이 함수는 왜 void 타입?
    //      메서드 호출 후 반환값이 필요하지 않기 때문, 하지만 추후에는 BlogEntity 타입으로 바꿔줘도 됨.
    // 2. controller에서 DTO로 받은 데이터를 엔터티로 변환한다.
    //  BlogEntity blogEntity = new BlogEntity();
    //  blogEntity.setTitle(blogDTO.getTitle());
    //      blogDTO의 title을 가져와서 blogEntity 의 Title 로 넣어준다.(content 도 동일)
    // 3. blogEntity 로 변환된 데이터를 레파지토리로 저장된다.

    // 근데 왜 DTO의 id 는 안바꿔줘도 되지? 자동으로 처리되나?
    //      savepost 매서드에서는 사용자가 직접 id 를 입력하지 않기 때문에 DTO 의 id를 처리하지 않아도 된다.
    //      하지만 수정이나 조회 작업이 id 가 필요함으로 DTO 에 id 는 만들어야 한다
    // 비즈니스 로직 = 블로그의 CRUD


    @Transactional
    public List<BlogDTO> getAllpost() {
        List<BlogEntity> blogEntities = blogRepository.findAll();
        // 레파지토리에 findall 함수를 가져온다
        List<BlogDTO> blogDTOList = new ArrayList<>();
        // 빈 DTO 리스트를 생성한다
        for (BlogEntity blogEntity : blogEntities) {
            // blogEntities 에 있는 BlogEntity 객체를 하나씩 처리하겠다(for each)
            // {} 안에 있는 건 반복되는 코드다
            // blogEntity 는 blogEntities 를 참조하는 변수.
            BlogDTO blogDTO = new BlogDTO(); // 각 엔터티 기반으로 DTO 객체를 생성
            blogDTO.setId(blogEntity.getId()); // entity 에서 ID 를 가져와서 DTO 에 담아줌.
            blogDTO.setTitle(blogEntity.getTitle());
            blogDTOList.add(blogDTO); // 가져온 blogDTO를 리스트에 추가한다.
        }
        return blogDTOList;
    }

    // blogDTO id를 가져와서 blogEntity id 에 넣어준다
    public BlogDTO getpost(Integer id) {
        BlogEntity blogEntity = blogRepository.findById(id).get();
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blogEntity.getId());
        blogDTO.setTitle(blogEntity.getTitle());
        blogDTO.setContent(blogEntity.getContents());
        return blogDTO;
    }

    @Transactional
    public void updatePost(BlogDTO existingPost) {
       BlogEntity blogEntity = blogRepository.findById(existingPost.getId()).get();
        blogEntity.setTitle(existingPost.getTitle());
        blogEntity.setContents(existingPost.getContent());

        // 레파지토리를 이용해서 저장
        blogRepository.save(blogEntity);
    }

    @Transactional
    public void deletePost(Integer id) {
        blogRepository.deleteById(id);
    }
}
