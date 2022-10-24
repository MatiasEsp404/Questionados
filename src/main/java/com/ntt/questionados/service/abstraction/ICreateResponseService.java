package com.ntt.questionados.service.abstraction;

import com.ntt.questionados.dto.request.CreateResponseRequest;
import com.ntt.questionados.dto.response.ResponseResponse;

public interface ICreateResponseService {

  ResponseResponse create(CreateResponseRequest request);

}
