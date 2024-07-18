package com.ohgiraffers.springcrud.dao;

import com.ohgiraffers.springcrud.model.entity.HYStudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HYStudyRepository extends JpaRepository<HYStudyEntity, Long> {

}
