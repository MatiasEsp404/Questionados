package com.ntt.questionados.service.abstraction;

import com.ntt.questionados.dto.response.CategoryResponse;
import com.ntt.questionados.dto.response.ListCategoriesResponse;
import org.springframework.data.domain.Pageable;

public interface IGetCategoryService {

	CategoryResponse getBy(Long id);
	ListCategoriesResponse paginatedCategories(Pageable pageable);
	
}
