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

    @Transactional
    public void savepost(HyMovieDTO hyMovieDTO) {
        HyMovieEntity hyMovieEntity = new HyMovieEntity();
        hyMovieEntity.setTitle(hyMovieDTO.getTitle());
        hyMovieEntity.setImageUrl(hyMovieDTO.getImageUrl());
        hyMovieEntity.setBestMent(hyMovieDTO.getBestMent());
        hyMovieEntity.setReviewComment(hyMovieDTO.getReviewComment());

        hyMovieRepository.save(hyMovieEntity);
    }



    @Transactional
    public List<HyMovieDTO> getAllpost() {
        List<HyMovieEntity> hyMovieEntities = hyMovieRepository.findAll();
        // △ 레파지토리에ㅣ findAll 함수를 가져온다
        List<HyMovieDTO> hyMovieDTOList = new ArrayList<>();
        // △ 빈 DTO 리스트를 생성한다
        for (HyMovieEntity hyMovieEntity : hyMovieEntities) {
            // hyMovieEntities 에 있는 Entity 객체를 하나씩 처리하겠다 (for each)
            // {} 안에 있는 건 반복되는 코드
            // hyMovieEntity는 hyMovieEntities를 참조하는 변수

            HyMovieDTO hyMovieDTO = new HyMovieDTO(); //각 엔티티 기반으로 DTO객체 생성
            hyMovieDTO.setId(hyMovieEntity.getId()); // entity에서 ID를 가져와서 DTO에 담아줌.
            hyMovieDTO.setTitle(hyMovieEntity.getTitle());

            hyMovieDTOList.add(hyMovieDTO); // 가져온 DTO를 리스트에 추가한다.
        }
        return hyMovieDTOList;
    }

    /* 상세조회 로직 */
    // DTO id를 가져와서 해당하는 정보를 Entity id에 넣어준다
    public HyMovieDTO getMoviePost(Integer id) {
    // getMoviePost 메서드는 id를 받아서 해당하는 영화 정보를 가져오는 기능을 수행한다

        // ▽ hyMovieRepository를 통해 id에 해당하는 HyMovieEntity 객체를 데이터베이스에서 조회
        // ▽ .get() 메서드는 Optional 타입에서 실제 객체를 추출한다.
        // ▽ 이 때, findById(id)의 결과가 null이 아니라는 가정 하에 사용
        HyMovieEntity hyMovieEntity = hyMovieRepository.findById(id).get();
        // ▽ 조회한 데이터를 담을 새로운 HyMovieDTO 객체를 생성
        HyMovieDTO hyMovieDTO = new HyMovieDTO();

        // ▽ 데이터를 Entity 객체에서 DTO 객체로 매핑
        hyMovieDTO.setId(hyMovieEntity.getId());
        hyMovieDTO.setTitle(hyMovieEntity.getTitle());
        hyMovieDTO.setImageUrl(hyMovieEntity.getImageUrl());
        hyMovieDTO.setBestMent(hyMovieEntity.getBestMent());
        hyMovieDTO.setReviewComment(hyMovieEntity.getReviewComment());
        // ▽ 매핑된 데이터 객체를 반환
        return hyMovieDTO;
        // 이 코드는 데이터베이스에서 특정 정보를 조회하여 DTO객체로 변환하는 과정을 수행하는 메서드이다.
    }



    /* 수정하기 */
    //트랜잭션은 데이터베이스 작업이 성공적으로 완료되거나, 중간에 문제가 생기면 모든 변경을 취소하여 데이터베이스의 일관성을 유지하는 것
    /* 트랜잭션 보충설명 *
    *  모든 일이 제대로 끝나야만, 데이터베이스에 그 변화가 적용됩니다.
    *  만약 중간에 하나라도 문제가 생기면, 데이터베이스는 처음 상태로 되돌아갑니다
    *  그래서 데이터베이스가 항상 올바른 상태를 유지할 수 있게 된다. */

    //이 어노테이션은 메소드가 호출될 때 트랜잭션을 시작하고, 메소드가 끝나면 트랜잭션을 종료한다.
    @Transactional
    public void updatePost(HyMovieDTO modifyPost) {
        //△ HyMovieDTO modifyPost는 메소드가 호출될 때 전달될 매개변수로, 수정할 게시물의 데이터를 담고 있는 객체
        //▽ 엔티티 소환. 레파지토리에서 findById로 게시물을 찾는다. modifyPost가 가져온 id로 데이터베이스 접근
        //▽ .get()은 찾은 결과를 실제객체로 반환한다.
        HyMovieEntity hyMovieEntity = hyMovieRepository.findById(modifyPost.getId()).get();

        //▽ 데이터베이스에서 찾은 게시물 객체의 데이터를 수정하는 부분입니다.
        //▽ setㅁㅁㅁ 메소드를 사용하여 modifyPost 객체에서 가져온 새로운 데이터로 변경(매핑)
        hyMovieEntity.setTitle(modifyPost.getTitle());
        hyMovieEntity.setImageUrl(modifyPost.getImageUrl());
        hyMovieEntity.setBestMent(modifyPost.getBestMent());
        hyMovieEntity.setReviewComment(modifyPost.getReviewComment());

        //▽ 수정된 데이터를 레파지토리를 이용하여 데이터베이스에 저장.
        hyMovieRepository.save(hyMovieEntity);
    }

    /* 삭제하기 */
    public void deletePost(Integer id) {
        hyMovieRepository.deleteById(id);
    }
}