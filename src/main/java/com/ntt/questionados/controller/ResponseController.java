package com.ntt.questionados.controller;

import com.ntt.questionados.config.pagination.PaginatedResultsRetrieved;
import com.ntt.questionados.config.security.common.Paths;
import com.ntt.questionados.dto.request.CreateResponseRequest;
import com.ntt.questionados.dto.request.PatchResponseRequest;
import com.ntt.questionados.dto.request.UpdateResponseRequest;
import com.ntt.questionados.dto.response.ListResponseResponse;
import com.ntt.questionados.dto.response.ResponseResponse;
import com.ntt.questionados.service.abstraction.ICreateResponseService;
import com.ntt.questionados.service.abstraction.IDeleteResponseService;
import com.ntt.questionados.service.abstraction.IGetResponseService;
import com.ntt.questionados.service.abstraction.IUpdateResponseService;
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

@RequestMapping(path = Paths.RESPONSES)
@RestController
public class ResponseController {

  @Autowired
  private ICreateResponseService createResponseService;

  @Autowired
  private IGetResponseService getResponseService;

  @Autowired
  private PaginatedResultsRetrieved paginatedResultsRetrieved;

  @Autowired
  private IUpdateResponseService updateResponseService;

  @Autowired
  private IDeleteResponseService deleteResponseService;

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

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseResponse> getBy(@PathVariable Long id) {

    ResponseResponse responseResponse = getResponseService.getBy(id);
    return ResponseEntity.ok(responseResponse);

  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListResponseResponse> paginateCategories(
      Pageable pageable,
      UriComponentsBuilder uriBuilder,
      HttpServletResponse response) {

    ListResponseResponse listResponseResponse =
        getResponseService.paginatedResponses(pageable);

    paginatedResultsRetrieved.addLinkHeaderOnPagedResourceRetrieval(
        uriBuilder, response, Paths.RESPONSES,
        listResponseResponse.getPage(),
        listResponseResponse.getTotalPages(),
        listResponseResponse.getSize());

    return ResponseEntity.ok().body(listResponseResponse);
  }

  @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseResponse> update(
      @Valid @RequestBody UpdateResponseRequest updateResponseRequest,
      @PathVariable Long id) {

    ResponseResponse responseResponse = updateResponseService.update(updateResponseRequest, id);
    return ResponseEntity.ok(responseResponse);

  }

  @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseResponse> patch(
      @Valid @RequestBody PatchResponseRequest patchResponseRequest,
      @PathVariable Long id) {

    ResponseResponse responseResponse = updateResponseService.patch(patchResponseRequest, id);
    return ResponseEntity.ok(responseResponse);

  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {

    deleteResponseService.delete(id);
    return ResponseEntity.noContent().build();

  }

}
