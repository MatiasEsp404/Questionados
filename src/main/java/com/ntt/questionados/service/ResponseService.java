package com.ntt.questionados.service;

import com.ntt.questionados.dto.request.CreateResponseRequest;
import com.ntt.questionados.dto.response.ResponseResponse;
import com.ntt.questionados.entity.ResponseEntity;
import com.ntt.questionados.mapper.ResponseMapper;
import com.ntt.questionados.repository.IResponseRepository;
import com.ntt.questionados.service.abstraction.ICreateResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService implements ICreateResponseService {

  @Autowired
  private IResponseRepository responseRepository;

  @Autowired
  private ResponseMapper responseMapper;

  @Override
  public ResponseResponse create(CreateResponseRequest request) {
    ResponseEntity responseEntity = responseMapper.toResponseEntity(request);
    responseEntity = responseRepository.save(responseEntity);
    return responseMapper.toResponseResponse(responseEntity);
  }
}
