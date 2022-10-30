package com.ntt.questionados.service;

import com.ntt.questionados.config.exception.runtime.EntityNotFoundException;
import com.ntt.questionados.config.pagination.GenericSetPagination;
import com.ntt.questionados.dto.request.CreateResponseRequest;
import com.ntt.questionados.dto.request.PatchResponseRequest;
import com.ntt.questionados.dto.request.UpdateResponseRequest;
import com.ntt.questionados.dto.response.ListResponseResponse;
import com.ntt.questionados.dto.response.ResponseResponse;
import com.ntt.questionados.entity.ResponseEntity;
import com.ntt.questionados.mapper.ResponseMapper;
import com.ntt.questionados.mapper.updater.ResponseUpdater;
import com.ntt.questionados.repository.IResponseRepository;
import com.ntt.questionados.service.abstraction.ICreateResponseService;
import com.ntt.questionados.service.abstraction.IDeleteResponseService;
import com.ntt.questionados.service.abstraction.IGetResponseService;
import com.ntt.questionados.service.abstraction.IUpdateResponseService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ResponseService extends GenericSetPagination<ResponseEntity> implements
    ICreateResponseService, IGetResponseService, IUpdateResponseService, IDeleteResponseService {

  @Autowired
  private IResponseRepository responseRepository;

  @Autowired
  private ResponseMapper responseMapper;

  @Autowired
  private ResponseUpdater responseUpdater;

  @Override
  public ResponseResponse create(CreateResponseRequest request) {
    ResponseEntity responseEntity = responseMapper.toResponseEntity(request);
    responseEntity = responseRepository.save(responseEntity);
    return responseMapper.toResponseResponse(responseEntity);
  }

  @Override
  public ResponseResponse getBy(Long id) {
    return responseMapper.toResponseResponse(findBy(id));
  }

  @Override
  public ListResponseResponse paginatedResponses(Pageable pageable) {
    Page<ResponseEntity> page = responseRepository.findAllByOrderByIdAsc(pageable);
    ListResponseResponse listResponseResponse = new ListResponseResponse();
    listResponseResponse.setResponses(responseMapper.toListResponseResponse(page.getContent()));
    setPagination(listResponseResponse, page);
    return listResponseResponse;
  }

  private ResponseEntity findBy(Long id) {
    Optional<ResponseEntity> responseEntity = responseRepository.findById(id);
    if (responseEntity.isEmpty()) {
      throw new EntityNotFoundException("Response not found.");
    }
    return responseEntity.get();
  }

  @Override
  public ResponseResponse update(UpdateResponseRequest updateResponseRequest, Long id) {
    // TODO creo que hay que agregar una llamada al findById para comprobar si existe
    ResponseEntity responseEntity = responseUpdater.toResponseEntity(updateResponseRequest, id);
    responseEntity = responseRepository.save(responseEntity);
    return responseMapper.toResponseResponse(responseEntity);
  }

  @Override
  public ResponseResponse patch(PatchResponseRequest patchResponseRequest, Long id) {
    ResponseEntity responseEntity = responseUpdater.toResponseEntity(patchResponseRequest,
        findById(id));
    responseEntity = responseRepository.save(responseEntity);
    return responseMapper.toResponseResponse(responseEntity);
  }

  private ResponseEntity findById(Long id) {
    Optional<ResponseEntity> responseEntity = responseRepository.findById(id);
    if (responseEntity.isEmpty()) {
      throw new EntityNotFoundException("Response not found.");
    }
    return responseEntity.get();
  }

  @Override
  public void delete(Long id) {
    if (!responseRepository.existsById(id)) {
      throw new EntityNotFoundException("Category not found.");
    }
    responseRepository.deleteById(id);
  }
}
