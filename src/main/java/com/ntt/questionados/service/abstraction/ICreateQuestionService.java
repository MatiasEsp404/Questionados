package com.ntt.questionados.service.abstraction;

import com.ntt.questionados.dto.request.CreateQuestionRequest;
import com.ntt.questionados.dto.response.QuestionResponse;

public interface ICreateQuestionService {

  QuestionResponse create(CreateQuestionRequest request);

}
