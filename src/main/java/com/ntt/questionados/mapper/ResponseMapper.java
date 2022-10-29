package com.ntt.questionados.mapper;

import com.ntt.questionados.config.exception.runtime.EntityNotFoundException;
import com.ntt.questionados.dto.request.CreateResponseRequest;
import com.ntt.questionados.dto.response.ResponseResponse;
import com.ntt.questionados.entity.QuestionEntity;
import com.ntt.questionados.entity.ResponseEntity;
import com.ntt.questionados.repository.IQuestionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {

  @Autowired
  private IQuestionRepository questionRepository;

  public List<ResponseResponse> toListResponseResponse(List<ResponseEntity> entities) {
    List<ResponseResponse> responseResponses = new ArrayList<>();
    for (ResponseEntity response : entities) {
      responseResponses.add(toResponseResponse(response));
    }
    return responseResponses;
  }

  public ResponseResponse toResponseResponse(ResponseEntity entity) {
    return ResponseResponse.builder()
        .id(entity.getId())
        .description(entity.getDescription())
        .isRight(entity.getIsRight())
        .build();
  }

  public List<ResponseEntity> toListResponseEntity(List<CreateResponseRequest> responses) {
    List<ResponseEntity> responseEntities = new ArrayList<>();
    for (CreateResponseRequest response : responses) {
      responseEntities.add(toResponseEntity(response));
    }
    return responseEntities;
  }

  public ResponseEntity toResponseEntity(CreateResponseRequest response) {
    return ResponseEntity.builder()
        .description(response.getDescription())
        .isRight(response.getIsRight())
        .question(findQuestion(response.getQuestionId()))
        .build();
  }

  private QuestionEntity findQuestion(Long id) {
    Optional<QuestionEntity> questionEntity = questionRepository.findById(id);
    if (questionEntity.isEmpty()) {
      throw new EntityNotFoundException("Question not found");
    }
    return questionEntity.get();
  }
}


