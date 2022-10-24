package com.ntt.questionados.repository;

import com.ntt.questionados.entity.ResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResponseRepository extends JpaRepository<ResponseEntity, Long> {

}
