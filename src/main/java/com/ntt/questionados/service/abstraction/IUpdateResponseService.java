package com.ntt.questionados.service.abstraction;

import com.ntt.questionados.dto.request.PatchResponseRequest;
import com.ntt.questionados.dto.request.UpdateResponseRequest;
import com.ntt.questionados.dto.response.ResponseResponse;

public interface IUpdateResponseService {

  ResponseResponse update(UpdateResponseRequest updateResponseRequest, Long id);

  ResponseResponse patch(PatchResponseRequest patchResponseRequest, Long id);

}
