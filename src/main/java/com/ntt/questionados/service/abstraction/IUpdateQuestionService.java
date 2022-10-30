package com.ntt.questionados.service.abstraction;

import com.ntt.questionados.dto.request.PatchQuestionRequest;
import com.ntt.questionados.dto.request.UpdateQuestionRequest;
import com.ntt.questionados.dto.response.QuestionResponse;

public interface IUpdateQuestionService {

  QuestionResponse update(UpdateQuestionRequest updateQuestionRequest, Long id);

  QuestionResponse patch(PatchQuestionRequest patchQuestionRequest, Long id);

}
