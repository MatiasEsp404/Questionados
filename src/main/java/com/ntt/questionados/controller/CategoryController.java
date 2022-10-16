package com.ntt.questionados.controller;

import java.net.URI;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ntt.questionados.dto.request.CreateCategoryRequest;
import com.ntt.questionados.dto.request.PatchCategoryRequest;
import com.ntt.questionados.dto.request.UpdateCategoryRequest;
import com.ntt.questionados.dto.response.CategoryResponse;
import com.ntt.questionados.service.abstraction.ICreateCategoryService;
import com.ntt.questionados.service.abstraction.IDeleteCategoryService;
import com.ntt.questionados.service.abstraction.IGetCategoryService;
import com.ntt.questionados.service.abstraction.IUpdateCategoryService;

@RequestMapping("/api/categories")
@RestController
public class CategoryController {

	@Autowired
	private ICreateCategoryService createCategoryService;

	@Autowired
	private IGetCategoryService getCategoryService;

	@Autowired
	private IUpdateCategoryService updateCategoryService;

	@Autowired
	private IDeleteCategoryService deleteCategoryService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
		CategoryResponse categoryResponse = createCategoryService.create(createCategoryRequest);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoryResponse.getId()).toUri();
		return ResponseEntity.created(location).body(categoryResponse);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryResponse>> getAll() {
		List<CategoryResponse> categoryResponses = getCategoryService.getAll();
		return ResponseEntity.ok(categoryResponses);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> getBy(@PathVariable Long id) {
		CategoryResponse categoryResponse = getCategoryService.getBy(id);
		return ResponseEntity.ok(categoryResponse);
	}

	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> update(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest,
			@PathVariable Long id) {
		CategoryResponse categoryResponse = updateCategoryService.update(updateCategoryRequest, id);
		return ResponseEntity.ok(categoryResponse);
	}

	@PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> patch(@Valid @RequestBody PatchCategoryRequest patchCategoryRequest,
			@PathVariable Long id) {
		CategoryResponse categoryResponse = updateCategoryService.patch(patchCategoryRequest, id);
		return ResponseEntity.ok(categoryResponse);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		deleteCategoryService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
