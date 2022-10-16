package com.ntt.questionados.service.abstraction;

import com.ntt.questionados.dto.request.CreateCategoryRequest;
import com.ntt.questionados.dto.response.CategoryResponse;

public interface ICreateCategoryService {

	CategoryResponse create(CreateCategoryRequest request);
	
}
