package com.ntt.questionados.service;

import com.ntt.questionados.config.exception.runtime.EntityNotFoundException;
import com.ntt.questionados.config.exception.runtime.WrongResponseException;
import com.ntt.questionados.dto.request.QuestionadosRequest;
import com.ntt.questionados.dto.response.NextQuestionResponse;
import com.ntt.questionados.dto.response.QuestionadosResponse;
import com.ntt.questionados.entity.QuestionEntity;
import com.ntt.questionados.entity.ResponseEntity;
import com.ntt.questionados.mapper.QuestionMapper;
import com.ntt.questionados.mapper.ResponseMapper;
import com.ntt.questionados.repository.ICategoryRepository;
import com.ntt.questionados.repository.IQuestionRepository;
import com.ntt.questionados.repository.IResponseRepository;
import com.ntt.questionados.service.abstraction.IQuestionadosService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionadosService implements IQuestionadosService {

  @Autowired
  private IQuestionRepository questionRepository;

  @Autowired
  private IResponseRepository responseRepository;

  @Autowired
  private ICategoryRepository categoryRepository;

  @Autowired
  private QuestionMapper questionMapper;

  @Autowired
  private ResponseMapper responseMapper;

  @Override
  public NextQuestionResponse next() {
    List<QuestionEntity> questionEntities = questionRepository.findAll();
    if (questionEntities.isEmpty()) {
      throw new EntityNotFoundException("Questions not found.");
    }
    return buildNextQuestionResponse(questionEntities
        .get(generateRandomNumber(questionEntities.size())));
  }

  private NextQuestionResponse buildNextQuestionResponse(QuestionEntity questionEntity) {
    return NextQuestionResponse.builder()
        .question(questionMapper.toQuestionResponse(questionEntity))
        .responses(responseMapper.toListResponseResponse(
            responseRepository.findAllByQuestion(questionEntity)))
        .build();
  }

  private int generateRandomNumber(Integer max) {
    return (int) (Math.random() * max);
  }

  @Override
  public QuestionadosResponse game(QuestionadosRequest request) {
    if (!questionRepository.existsById(request.getQuestionId())) {
      throw new EntityNotFoundException("Question not found");
    }
    ResponseEntity response = findResponseBy(request.getResponseId());
    if (!Objects.equals(request.getQuestionId(), response.getQuestion().getId())) {
      throw new WrongResponseException("The response does not belong to the question.");
    }
    return QuestionadosResponse.builder().isRight(response.getIsRight()).build();
  }

  private ResponseEntity findResponseBy(Long id) {
    Optional<ResponseEntity> entity = responseRepository.findById(id);
    if (entity.isEmpty()) {
      throw new EntityNotFoundException("Response not found");
    }
    return entity.get();
  }

}
