package com.ntt.questionados.mapper.updater;

import com.ntt.questionados.config.exception.runtime.EntityNotFoundException;
import com.ntt.questionados.dto.request.PatchQuestionRequest;
import com.ntt.questionados.dto.request.UpdateQuestionRequest;
import com.ntt.questionados.entity.CategoryEntity;
import com.ntt.questionados.entity.QuestionEntity;
import com.ntt.questionados.repository.ICategoryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionUpdater {

  @Autowired
  private ICategoryRepository categoryRepository;

  public QuestionEntity toQuestionEntity(UpdateQuestionRequest request, Long id) {
    return QuestionEntity.builder()
        .id(id)
        .question(request.getQuestion())
        .categoryEntity(findBy(request.getCategoryId()))
        .build();
  }

  private CategoryEntity findBy(Long id) {
    Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
    if (categoryEntity.isEmpty()) {
      throw new EntityNotFoundException("Category not found");
    }
    return categoryEntity.get();
  }

  public QuestionEntity toQuestionEntity(PatchQuestionRequest request, QuestionEntity entity){
    String question = request.getQuestion();
    if (question != null){
      entity.setQuestion(question);
    }
    Long categoryId = request.getCategoryId();
    if (categoryId != null){
      entity.setCategoryEntity(findBy(categoryId));
    }
    return entity;
  }

}
