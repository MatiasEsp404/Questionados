package com.ntt.questionados.controller;

import com.ntt.questionados.config.pagination.PaginatedResultsRetrieved;
import com.ntt.questionados.config.security.common.Paths;
import com.ntt.questionados.dto.request.CreateQuestionRequest;
import com.ntt.questionados.dto.request.PatchQuestionRequest;
import com.ntt.questionados.dto.request.UpdateQuestionRequest;
import com.ntt.questionados.dto.response.ListQuestionResponse;
import com.ntt.questionados.dto.response.QuestionResponse;
import com.ntt.questionados.service.abstraction.ICreateQuestionService;
import com.ntt.questionados.service.abstraction.IDeleteQuestionService;
import com.ntt.questionados.service.abstraction.IGetQuestionService;
import com.ntt.questionados.service.abstraction.IUpdateQuestionService;
import java.net.URI;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping(path = Paths.QUESTIONS)
@RestController
public class QuestionController {

  @Autowired
  private IGetQuestionService getQuestionService;

  @Autowired
  private ICreateQuestionService createQuestionService;

  @Autowired
  private PaginatedResultsRetrieved paginatedResultsRetrieved;

  @Autowired
  private IUpdateQuestionService updateQuestionService;

  @Autowired
  private IDeleteQuestionService deleteQuestionService;

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

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListQuestionResponse> paginateQuestions(
      Pageable pageable, UriComponentsBuilder uriBuilder, HttpServletResponse response) {

    ListQuestionResponse listQuestionResponse = getQuestionService.paginatedQuestions(pageable);

    paginatedResultsRetrieved.addLinkHeaderOnPagedResourceRetrieval(
        uriBuilder, response, Paths.CATEGORIES,
        listQuestionResponse.getPage(),
        listQuestionResponse.getTotalPages(),
        listQuestionResponse.getSize());
    return ResponseEntity.ok().body(listQuestionResponse);

  }

  @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<QuestionResponse> update(
      @Valid @RequestBody UpdateQuestionRequest updateQuestionRequest,
      @PathVariable Long id) {

    QuestionResponse questionResponse = updateQuestionService.update(updateQuestionRequest, id);
    return ResponseEntity.ok(questionResponse);
  }

  @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<QuestionResponse> patch(
      @Valid @RequestBody PatchQuestionRequest patchQuestionRequest,
      @PathVariable Long id) {

    QuestionResponse questionResponse = updateQuestionService.patch(patchQuestionRequest, id);
    return ResponseEntity.ok(questionResponse);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteQuestionService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
