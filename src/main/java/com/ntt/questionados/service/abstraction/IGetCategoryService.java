package com.ntt.questionados.service.abstraction;

import com.ntt.questionados.dto.response.CategoryResponse;
import java.util.List;

public interface IGetCategoryService {

	List<CategoryResponse> getAll();
	CategoryResponse getBy(Long id);
	
}
