package com.ntt.questionados.repository;

import com.ntt.questionados.entity.QuestionEntity;
import com.ntt.questionados.entity.ResponseEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResponseRepository extends JpaRepository<ResponseEntity, Long> {

  Page<ResponseEntity> findAllByOrderByIdAsc(Pageable pageable);

  List<ResponseEntity> findAllByQuestion(QuestionEntity question);

}
