package com.ohgiraffers.springcrud.service;

import com.ohgiraffers.springcrud.dao.HYStudyRepository;
import com.ohgiraffers.springcrud.model.dto.HYStudyDTO;
import com.ohgiraffers.springcrud.model.entity.HYStudyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HYStudyService {

    // MVC 패턴에 따라 Repository 클래스를 선언
    private final HYStudyRepository hyStudyRepository;


    @Autowired // 자동주입 어노테이션
    public HYStudyService(HYStudyRepository hyStudyRepository) {
        //생성자를 통해 레파지토리 인스턴스를 저장
        this.hyStudyRepository = hyStudyRepository;
    }


    /* [등록하기] */
    /* @Transactional //데이터의 일관성과 무결성을 유지하는 데 중요한 역할
    * 트랜잭션 경계 설정: 메소드나 클래스에 @Transactional을 적용하여 트랜잭션 경계를 설정합니다.
    * 자동 롤백: 트랜잭션 내에서 예외가 발생하면 자동으로 롤백됩니다.
    * 전파 및 격리 수준 설정: 트랜잭션의 전파 방식과 격리 수준을 설정할 수 있습니다.
    * 읽기 전용 최적화: 트랜잭션이 읽기 전용인 경우 성능 최적화를 위해 readOnly=true로 설정할 수 있습니다.
    * */
    @Transactional
    //▽ 컨트롤러에서 보낸 데이터를 저장할 메소드
    public HYStudyDTO savepost(HYStudyDTO hyStudyDTO) {
    //                  △컨트롤러에서 DTO에 바인딩해 보낸걸 받음

        //▽ 엔티티 클래스를 선언초기화 //jpa를 활용하여 데이터베이스에 저장하기 위함
        HYStudyEntity hyStudyEntity = new HYStudyEntity();
        //▽ DTO가 get한 데이터를 엔티티에 set한다.
        hyStudyEntity.setTitle(hyStudyDTO.getTitle()); //엔티티에 .set(DTO가 .get);
        hyStudyEntity.setContents(hyStudyDTO.getContents()); //엔티티에 .set(DTO가 .get)
        //▽ set해준 엔티티를 레파지토리를 이용하여 저장
        hyStudyRepository.save(hyStudyEntity);

        return hyStudyDTO;
    }


    /* [전체조회] */
    // 등록된거 확인하기
    @Transactional
    // ▽ 모든 게시물을 조회하여 HYStudyDTO 리스트로 반환하는 메소드
    public List<HYStudyDTO> viewAllposts(){
        // List : 순차적으로 데이터를 저장할 수 있는 자료 구조
        // ▽ Repository 객체는 데이터베이스에 접근하여 CRUD (Create, Read, Update, Delete) 작업을 수행
        List<HYStudyEntity> hyStudyEntities = hyStudyRepository.findAll();
        //△ 데이터베이스에서 조회한 Entity 객체들의 리스트를 저장할 변수를 선언
        //△ 레파지토리의 findAll() 메소드를 호출하여 데이터베이스의 모든 Entity 객체들을 조회
        List<HYStudyDTO> hyStudyDTOList = new ArrayList<>();
        //△ 빈 DTO 리스트를 생성한다 //빈 바구니. 등록된 데이터를 담을거야!
        for (HYStudyEntity hyStudyEntity:hyStudyEntities){
            //ㅁㅁEntities에 있는 Entity 객체들을 하나씩 처리하겠다 (for each)
            // {} 안에 있는 건 반복되는 코드
            //()안에 선언한 ㅁㅁEntity는 ㅁㅁEntities를 참조하는 변수

            HYStudyDTO hyStudyDTO = new HYStudyDTO();
            //△ 각 엔티티 기반으로 DTO객체를 생성한다

            //▽ entity에서 id를 가져와서 DTO에 set.
            hyStudyDTO.setId(hyStudyEntity.getId());
            //▽ entity에서 title을 get해서 DTO에 set.
            hyStudyDTO.setTitle(hyStudyEntity.getTitle());

            //set해서 가져온 DTO를 리스트에 추가(add)한다.
            hyStudyDTOList.add(hyStudyDTO);
        }
        // 다 담았다! 리턴! -> 컨트롤러로 리스트를 보내줌. 컨트롤러에서 뷰로 보낼 수 있도록.
        return hyStudyDTOList;
    }


    /* [상세조회] */
    // DTO id를 가져와서 해당하는 정보를 Entity id에 넣어준다
    public HYStudyDTO postView(Long id){
    // 이 메서드는 id를 받아서 해당하는 정보를 가져오는 기능을 수행
        // ▽ 데이터베이스에 저장된 엔티티를 가져오겠다.
        // ▽ Repository를 통해 id에 해당하는 Entity 객체를 데이터베이스에서 조회한다.
        HYStudyEntity hyStudyEntity = hyStudyRepository.findById(id).get();
//        Optional<HYStudyEntity> optionalEntity = hyStudyRepository.findById(id);
        /*// △ .get() 메서드는 실제 객체를 추출한다. <- findById(id)의 결과가 null이 아니라는 가정 하에 사용
        * Optional을 사용하면서 .get() 메서드를 사용할 수 없게 된다.//findById(id).get() 으로 사용했었는데 옵셔널 추가하면서 오류남
        * Optional 객체는 값이 존재하지 않을 수도 있기 때문에, 강제로 값을 추출하는 .get() 메서드는 사용하지 않도록 권장된다.
        * 이는 NoSuchElementException을 발생시킬 수 있기 때문이다. */
        // △ Optional 사용: findById(id) 메서드는 Optional<HYStudyEntity>를 반환한다. 이를 optionalEntity라는 변수에 저장.

/*        // Optional의 값이 존재하지 않으면 Exception을 던집니다.
        HYStudyEntity hyStudyEntity = optionalEntity.orElseThrow(
                () -> new Exception("게시글이 존재하지 않습니다."));
        // △ 값의 존재 여부 확인: optionalEntity.orElseThrow() 메서드를 사용하여 Optional에서 값을 안전하게 추출한다.
        // 값이 존재하면 해당 값이 반환되고, 값이 존재하지 않으면 Exception이 발생한다.*/

        // ▽ 조회한 데이터를 담을 새로운 DTO 객체를 생성
        HYStudyDTO hyStudyDTO = new HYStudyDTO();
        // ▽ DTO에 set한다. 엔티티에서 조회하여 get한 데이터를.
        // ▽ == 데이터를 Entity 객체에서 DTO 객체로 매핑
        hyStudyDTO.setId(hyStudyEntity.getId());
        hyStudyDTO.setTitle(hyStudyEntity.getTitle());
        hyStudyDTO.setContents(hyStudyEntity.getContents());

        // ▽ set이 완료된 DTO 객체를 반환
        // ▽ == 매핑된 데이터 객체를 반환
        return hyStudyDTO;
        // 이 코드는 데이터베이스에서 특정(id)정보를 조회하여 DTO객체로 변환하는 과정을 수행하는 메서드이다.

    }

    // 수정한 데이터를 저장(업데이트)한다.
    @Transactional
    public HYStudyDTO postUpdate(HYStudyDTO updateDTO) {
        HYStudyEntity hyStudyEntity = hyStudyRepository.findById(updateDTO.getId()).get();
        hyStudyEntity.setTitle(updateDTO.getTitle());
        hyStudyEntity.setContents(updateDTO.getContents());
        hyStudyRepository.save(hyStudyEntity);

        return updateDTO;
    }



    /* [수정하기] */
    //어떤 포스트를 수정할지 조회 먼저 한다





/* GPT최적화 코드..해결X     *//* [전체조회] *//*
    // 등록된거 확인하기
    @Transactional
    // ▽ 모든 게시물을 조회하여 HYStudyDTO 리스트로 반환하는 메소드
    public List<HYStudyDTO> viewAllposts(){
        // List : 순차적으로 데이터를 저장할 수 있는 자료 구조
        // ▽ hyStudyRepository 객체는 데이터베이스에 접근하여 CRUD (Create, Read, Update, Delete) 작업을 수행
        List<HYStudyEntity> hyStudyEntities = hyStudyRepository.findAll();
        //△ 데이터베이스에서 조회한 HYStudyEntity 객체들의 리스트를 저장할 변수를 선언
        //△ 리포지토리의 findAll() 메소드를 호출하여 데이터베이스의 모든 HYStudyEntity 객체들을 조회
        return hyStudyEntities.stream()
                .map(this::convertToDTO) // convertToDTO 메소드를 사용하여 변환
                .collect(Collectors.toList());

    }
    private HYStudyDTO convertToDTO(HYStudyEntity hyStudyEntity){
        HYStudyDTO hyStudyDTO = new HYStudyDTO();
        hyStudyDTO.setId(hyStudyEntity.getId());
        hyStudyDTO.setTitle(hyStudyDTO.getTitle());

        return hyStudyDTO;
    }*/
}
