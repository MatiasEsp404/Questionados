package com.ntt.questionados.service.abstraction;

import com.ntt.questionados.dto.response.ListQuestionResponse;
import com.ntt.questionados.dto.response.QuestionResponse;
import org.springframework.data.domain.Pageable;

public interface IGetQuestionService {

  QuestionResponse getBy(Long id);
  ListQuestionResponse paginatedQuestions(Pageable pageable);

}
