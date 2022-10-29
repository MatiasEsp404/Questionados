package com.ntt.questionados.mapper.updater;

import com.ntt.questionados.config.exception.runtime.EntityNotFoundException;
import com.ntt.questionados.dto.request.PatchResponseRequest;
import com.ntt.questionados.dto.request.UpdateResponseRequest;
import com.ntt.questionados.entity.QuestionEntity;
import com.ntt.questionados.entity.ResponseEntity;
import com.ntt.questionados.repository.IQuestionRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseUpdater {

  @Autowired
  private IQuestionRepository questionRepository;

  public ResponseEntity toResponseEntity(UpdateResponseRequest request, Long id) {
    return ResponseEntity.builder()
        .id(id)
        .description(request.getDescription())
        .isRight(request.getIsRight())
        .question(findById(request.getQuestionId()))
        .build();
  }

  private QuestionEntity findById(Long id) {
    Optional<QuestionEntity> questionEntity = questionRepository.findById(id);
    if (questionEntity.isEmpty()) {
      throw new EntityNotFoundException("Question not found.");
    }
    return questionEntity.get();
  }

  public ResponseEntity toResponseEntity(PatchResponseRequest request, ResponseEntity entity) {
    String description = request.getDescription();
    if (description != null) {
      entity.setDescription(description);
    }
    Boolean isRight = request.getIsRight();
    if (isRight != null) {
      entity.setIsRight(isRight);
    }
    Long questionId = request.getQuestionId();
    if (questionId != null) {
      entity.setQuestion(findById(questionId));
    }
    return entity;
  }
}
