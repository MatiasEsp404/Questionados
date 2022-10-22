package com.ntt.questionados.service.abstraction;

import com.ntt.questionados.dto.response.CategoryResponse;
import com.ntt.questionados.dto.response.ListCategoriesResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface IGetCategoryService {

//	List<CategoryResponse> getAll();
	CategoryResponse getBy(Long id);
	ListCategoriesResponse paginatedCategories(Pageable pageable);
	
}
