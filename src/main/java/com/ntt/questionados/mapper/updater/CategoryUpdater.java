package com.ntt.questionados.mapper.updater;

import org.springframework.stereotype.Component;

import com.ntt.questionados.dto.request.PatchCategoryRequest;
import com.ntt.questionados.dto.request.UpdateCategoryRequest;
import com.ntt.questionados.entity.CategoryEntity;

@Component
public class CategoryUpdater {

	public CategoryEntity toCategoryEntity(UpdateCategoryRequest updateCategoryRequest, Long id) {
		return CategoryEntity.builder().id(id).name(updateCategoryRequest.getName())
				.description(updateCategoryRequest.getDescription()).build();
	}

	public CategoryEntity toCategoryEntity(PatchCategoryRequest patchCategoryRequest, CategoryEntity categoryEntity) {
		String name = patchCategoryRequest.getName();
		if(name != null) {
			categoryEntity.setName(name);
		}
		String description = patchCategoryRequest.getDescription();
		if(description != null) {
			categoryEntity.setDescription(description);
		}
		return categoryEntity;
	}

}
