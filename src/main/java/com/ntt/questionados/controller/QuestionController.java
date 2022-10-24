package com.ntt.questionados.controller;

import com.ntt.questionados.config.security.common.Paths;
import com.ntt.questionados.dto.request.CreateQuestionRequest;
import com.ntt.questionados.dto.response.QuestionResponse;
import com.ntt.questionados.service.abstraction.ICreateQuestionService;
import com.ntt.questionados.service.abstraction.IGetQuestionService;
import java.net.URI;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(path = Paths.QUESTIONS)
@RestController
public class QuestionController {

  @Autowired
  private IGetQuestionService getQuestionService;

  @Autowired
  private ICreateQuestionService createQuestionService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<QuestionResponse> create(
      @Valid @RequestBody CreateQuestionRequest createQuestionRequest) {

    QuestionResponse questionResponse = createQuestionService.create(createQuestionRequest);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(questionResponse.getId())
        .toUri();

    return ResponseEntity.created(location).body(questionResponse);

  }

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<QuestionResponse> getBy(@PathVariable Long id) {
    QuestionResponse questionResponse = getQuestionService.getBy(id);
    return ResponseEntity.ok(questionResponse);
  }

}
