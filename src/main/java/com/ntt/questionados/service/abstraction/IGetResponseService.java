package com.ntt.questionados.service.abstraction;

import com.ntt.questionados.dto.response.ListResponseResponse;
import com.ntt.questionados.dto.response.ResponseResponse;
import org.springframework.data.domain.Pageable;

public interface IGetResponseService {

  ResponseResponse getBy(Long id);

  ListResponseResponse paginatedResponses(Pageable pageable);

}
