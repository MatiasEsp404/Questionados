package com.ntt.questionados.service;

import com.ntt.questionados.config.exception.runtime.EntityNotFoundException;
import com.ntt.questionados.config.pagination.GenericSetPagination;
import com.ntt.questionados.dto.request.CreateQuestionRequest;
import com.ntt.questionados.dto.response.ListQuestionResponse;
import com.ntt.questionados.dto.response.QuestionResponse;
import com.ntt.questionados.entity.CategoryEntity;
import com.ntt.questionados.entity.QuestionEntity;
import com.ntt.questionados.mapper.QuestionMapper;
import com.ntt.questionados.repository.IQuestionRepository;
import com.ntt.questionados.service.abstraction.ICreateQuestionService;
import com.ntt.questionados.service.abstraction.IGetQuestionService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionService extends GenericSetPagination<QuestionService> implements
    ICreateQuestionService, IGetQuestionService {

  @Autowired
  private IQuestionRepository questionRepository;

  @Autowired
  private QuestionMapper questionMapper;

  @Override
  public QuestionResponse create(CreateQuestionRequest request) {
    QuestionEntity questionEntity = questionMapper.toQuestionEntity(request);
    questionEntity = questionRepository.save(questionEntity);
    return questionMapper.toQuestionResponse(questionEntity);
  }

  @Override
  public QuestionResponse getBy(Long id) {
    return questionMapper.toQuestionResponse(findById(id));
  }

  private QuestionEntity findById(Long id) {
    Optional<QuestionEntity> questionEntity = questionRepository.findById(id);
    if (questionEntity.isEmpty()) {
      throw new EntityNotFoundException("Question not found.");
    }
    return questionEntity.get();
  }

  @Override
  public ListQuestionResponse paginatedQuestions(Pageable pageable) {
    return null;
  }

}
