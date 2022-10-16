package com.ntt.questionados.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntt.questionados.dto.request.CreateCategoryRequest;
import com.ntt.questionados.dto.request.PatchCategoryRequest;
import com.ntt.questionados.dto.request.UpdateCategoryRequest;
import com.ntt.questionados.dto.response.CategoryResponse;
import com.ntt.questionados.entity.CategoryEntity;
import com.ntt.questionados.exception.EntityNotFoundException;
import com.ntt.questionados.mapper.CategoryMapper;
import com.ntt.questionados.mapper.updater.CategoryUpdater;
import com.ntt.questionados.repository.ICategoryRepository;
import com.ntt.questionados.service.abstraction.ICreateCategoryService;
import com.ntt.questionados.service.abstraction.IDeleteCategoryService;
import com.ntt.questionados.service.abstraction.IGetCategoryService;
import com.ntt.questionados.service.abstraction.IUpdateCategoryService;

@Service
public class CategoryService implements ICreateCategoryService, IGetCategoryService, IUpdateCategoryService, IDeleteCategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private CategoryUpdater categoryUpdater;

	@Override
	public CategoryResponse create(CreateCategoryRequest request) {
		CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(request);
		categoryEntity = categoryRepository.save(categoryEntity);
		return categoryMapper.toCategoryResponse(categoryEntity);
	}

	@Override
	public List<CategoryResponse> getAll() {
		List<CategoryEntity> categoryEntities = categoryRepository.findAll();
		return categoryMapper.toListCategoryResponse(categoryEntities);
	}

	@Override
	public CategoryResponse getBy(Long id) {
		return categoryMapper.toCategoryResponse(findById(id));
	}

	@Override
	public CategoryResponse update(UpdateCategoryRequest updateCategoryRequest, Long id) {
		CategoryEntity categoryEntity = categoryUpdater.toCategoryEntity(updateCategoryRequest, id);
		categoryEntity = categoryRepository.save(categoryEntity);
		return categoryMapper.toCategoryResponse(categoryEntity);
	}

	@Override
	public CategoryResponse patch(PatchCategoryRequest patchCategoryRequest, Long id) {
		CategoryEntity categoryEntity = categoryUpdater.toCategoryEntity(patchCategoryRequest, findById(id));
		categoryEntity = categoryRepository.save(categoryEntity);
		return categoryMapper.toCategoryResponse(categoryEntity);
	}
	
	private CategoryEntity findById(Long id) {
		Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
		if(categoryEntity.isEmpty()) {
			throw new EntityNotFoundException("Category not found.");
		}
		return categoryEntity.get();
	}

	@Override
	public void delete(Long id) {
		if(categoryRepository.existsById(id)) {
			categoryRepository.deleteById(id);
		}
		throw new EntityNotFoundException("Category not found.");
	}
}