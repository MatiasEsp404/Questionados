package com.ntt.questionados.service.abstraction;

import com.ntt.questionados.dto.request.QuestionadosRequest;
import com.ntt.questionados.dto.response.NextQuestionResponse;
import com.ntt.questionados.dto.response.QuestionadosResponse;

public interface IQuestionadosService {

  NextQuestionResponse next();

  QuestionadosResponse game(QuestionadosRequest request);

}
