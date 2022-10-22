package com.ntt.questionados.config.pagination;

import org.springframework.data.domain.Page;

public class GenericSetPagination<T> {

  public void setPagination(PaginationResponse paginationResponse, Page<T> page) {
    paginationResponse.setPage(page.getNumber());
    paginationResponse.setTotalPages(page.getTotalPages());
    paginationResponse.setSize(page.getSize());
  }

}
