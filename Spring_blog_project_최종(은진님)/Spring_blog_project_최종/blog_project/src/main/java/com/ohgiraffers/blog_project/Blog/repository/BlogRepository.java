package com.ohgiraffers.blog_project.Blog.repository;

// 이거를 interface 로 만들어야  CRUD 를 쉽게 만들 수 있다.


import com.ohgiraffers.blog_project.Blog.model.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {
}


// BlogRepositoy를 JPARepository 로  확장한다, -> 데이터베이스와의 상호작용을 간편하게 처리할 수 있다.
// JpaRepository<BlogEntity, Integer> : BlogEntity 와 관련된 데이터베이스 작업을 처리하는 레포지토리 인터페이스임을 나타냄(Integer 는 ID 타입이야)
// JPARepository 인터페이스는 데이터베이스와 상호작용을 간편하게 처리할 수 있다.
// JPA Repository 가 제공하는 함수
//  1) save(S entity): 엔티티를 저장하거나 업데이트합니다.
//  2) findById(ID id): 주어진 ID로 엔티티를 조회합니다.
//  3) findAll(): 모든 엔티티를 조회합니다.
//  4) deleteById(ID id): 주어진 ID로 엔티티를 삭제합니다.
//  5) existsById(ID id): 주어진 ID의 엔티티가 존재하는지 확인합니다.
//
