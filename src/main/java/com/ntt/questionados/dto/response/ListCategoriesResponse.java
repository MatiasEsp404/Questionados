package com.ntt.questionados.dto.response;

import com.ntt.questionados.config.pagination.PaginationResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListCategoriesResponse extends PaginationResponse {

  private List<CategoryResponse> categories;

}
