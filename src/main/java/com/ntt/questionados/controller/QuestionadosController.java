package com.ntt.questionados.controller;

import com.ntt.questionados.config.security.common.Paths;
import com.ntt.questionados.dto.request.QuestionadosRequest;
import com.ntt.questionados.dto.response.NextQuestionResponse;
import com.ntt.questionados.dto.response.QuestionadosResponse;
import com.ntt.questionados.service.abstraction.IQuestionadosService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = Paths.QUESTIONADOS)
@RestController
public class QuestionadosController {

  @Autowired
  private IQuestionadosService questionadosService;

  @GetMapping(path = Paths.NEXT_QUESTION, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<NextQuestionResponse> next() {
    NextQuestionResponse response = questionadosService.next();
    return ResponseEntity.ok(response);
  }

  @PostMapping(path = Paths.CHECK_RESPONSE,
      produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<QuestionadosResponse> game(
      @Valid @RequestBody QuestionadosRequest request) {
    QuestionadosResponse response = questionadosService.game(request);
    return ResponseEntity.ok(response);
  }

}
