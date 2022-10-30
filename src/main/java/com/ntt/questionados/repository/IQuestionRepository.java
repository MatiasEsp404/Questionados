package com.ntt.questionados.repository;

import com.ntt.questionados.entity.CategoryEntity;
import com.ntt.questionados.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends JpaRepository<QuestionEntity, Long> {

  Page<QuestionEntity> findAllByOrderByIdAsc(Pageable pageable);

}
