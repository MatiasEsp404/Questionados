package com.ntt.questionados.service;

import com.ntt.questionados.config.exception.runtime.EntityNotFoundException;
import com.ntt.questionados.config.pagination.GenericSetPagination;
import com.ntt.questionados.dto.request.CreateQuestionRequest;
import com.ntt.questionados.dto.request.PatchQuestionRequest;
import com.ntt.questionados.dto.request.UpdateQuestionRequest;
import com.ntt.questionados.dto.response.ListQuestionResponse;
import com.ntt.questionados.dto.response.QuestionResponse;
import com.ntt.questionados.entity.QuestionEntity;
import com.ntt.questionados.mapper.QuestionMapper;
import com.ntt.questionados.mapper.updater.QuestionUpdater;
import com.ntt.questionados.repository.IQuestionRepository;
import com.ntt.questionados.service.abstraction.ICreateQuestionService;
import com.ntt.questionados.service.abstraction.IDeleteQuestionService;
import com.ntt.questionados.service.abstraction.IGetQuestionService;
import com.ntt.questionados.service.abstraction.IUpdateQuestionService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionService extends GenericSetPagination<QuestionEntity> implements
    ICreateQuestionService, IGetQuestionService, IUpdateQuestionService, IDeleteQuestionService {

  @Autowired
  private IQuestionRepository questionRepository;

  @Autowired
  private QuestionMapper questionMapper;

  @Autowired
  private QuestionUpdater questionUpdater;

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
    Page<QuestionEntity> page = questionRepository.findAllByOrderByIdAsc(pageable);
    ListQuestionResponse listQuestionResponse = new ListQuestionResponse();
    listQuestionResponse.setQuestions(questionMapper.toListQuestionResponse(page.getContent()));
    setPagination(listQuestionResponse, page);
    return listQuestionResponse;
  }

  @Override
  public QuestionResponse update(UpdateQuestionRequest updateQuestionRequest, Long id) {
    QuestionEntity questionEntity = questionUpdater.toQuestionEntity(updateQuestionRequest, id);
    questionEntity = questionRepository.save(questionEntity);
    return questionMapper.toQuestionResponse(questionEntity);
  }

  @Override
  public QuestionResponse patch(PatchQuestionRequest patchQuestionRequest, Long id) {
    QuestionEntity questionEntity = questionUpdater.toQuestionEntity(
        patchQuestionRequest, findById(id));
    questionEntity = questionRepository.save(questionEntity);
    return questionMapper.toQuestionResponse(questionEntity);
  }

  @Override
  public void delete(Long id) {
    if (!questionRepository.existsById(id)) {
      throw new EntityNotFoundException("Question not found.");
    }
    questionRepository.deleteById(id);
  }
}
