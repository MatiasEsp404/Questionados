package com.ntt.questionados.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.ntt.questionados.dto.request.CreateCategoryRequest;
import com.ntt.questionados.dto.response.CategoryResponse;
import com.ntt.questionados.entity.CategoryEntity;

@Component
public class CategoryMapper {

	public CategoryEntity toCategoryEntity(CreateCategoryRequest createCategoryRequest) {
		return CategoryEntity.builder()
				.name(createCategoryRequest.getName())
				.description(createCategoryRequest.getDescription())
				.build();
	}
	
	public CategoryResponse toCategoryResponse(CategoryEntity categoryEntity) {
		return CategoryResponse.builder()
				.id(categoryEntity.getId())
				.name(categoryEntity.getName())
				.description(categoryEntity.getDescription())
				.build();
	}

  public List<CategoryResponse> toListCategoryResponse(List<CategoryEntity> categoryEntities) {
		List<CategoryResponse> categoryResponses = new ArrayList<>();
		for(CategoryEntity categoryEntity : categoryEntities){
			categoryResponses.add(toCategoryResponse(categoryEntity));
		}
		return categoryResponses;
  }

}
