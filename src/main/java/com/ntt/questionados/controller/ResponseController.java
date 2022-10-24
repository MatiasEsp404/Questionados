package com.ntt.questionados.controller;

import com.ntt.questionados.config.security.common.Paths;
import com.ntt.questionados.dto.request.CreateResponseRequest;
import com.ntt.questionados.dto.response.ResponseResponse;
import com.ntt.questionados.service.abstraction.ICreateResponseService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(path = Paths.RESPONSES)
@RestController
public class ResponseController {

  @Autowired
  private ICreateResponseService createResponseService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseResponse> create(
      @Valid @RequestBody CreateResponseRequest createResponseRequest) {

    ResponseResponse responseResponse = createResponseService.create(createResponseRequest);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(responseResponse.getId())
        .toUri();

    return ResponseEntity.created(location).body(responseResponse);

  }


}
