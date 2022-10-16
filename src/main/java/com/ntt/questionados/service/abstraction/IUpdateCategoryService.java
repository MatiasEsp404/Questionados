package com.ntt.questionados.service.abstraction;

import com.ntt.questionados.dto.request.PatchCategoryRequest;
import com.ntt.questionados.dto.request.UpdateCategoryRequest;
import com.ntt.questionados.dto.response.CategoryResponse;

public interface IUpdateCategoryService {

	CategoryResponse update(UpdateCategoryRequest updateCategoryRequest, Long id);
	CategoryResponse patch(PatchCategoryRequest patchCategoryRequest, Long id);
	
}
