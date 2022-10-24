package com.ntt.questionados.controller;

import com.ntt.questionados.config.security.common.Paths;
import com.ntt.questionados.dto.response.ListCategoriesResponse;
import com.ntt.questionados.config.pagination.PaginatedResultsRetrieved;
import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping(path = Paths.CATEGORIES)
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

	@Autowired
	private PaginatedResultsRetrieved paginatedResultsRetrieved;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> create(
			@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {

		CategoryResponse categoryResponse = createCategoryService.create(createCategoryRequest);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoryResponse.getId()).toUri();
		return ResponseEntity.created(location).body(categoryResponse);

	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> getBy(@PathVariable Long id) {

		CategoryResponse categoryResponse = getCategoryService.getBy(id);
		return ResponseEntity.ok(categoryResponse);

	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ListCategoriesResponse> listActiveCategories(
			Pageable pageable,
			UriComponentsBuilder uriBuilder,
			HttpServletResponse response) {

		ListCategoriesResponse listCategoriesResponse =
				getCategoryService.paginatedCategories(pageable);

		paginatedResultsRetrieved.addLinkHeaderOnPagedResourceRetrieval(
				uriBuilder, response, "/categories",
				listCategoriesResponse.getPage(),
				listCategoriesResponse.getTotalPages(),
				listCategoriesResponse.getSize());
		return ResponseEntity.ok().body(listCategoriesResponse);

	}

	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> update(
			@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest,
			@PathVariable Long id) {

		CategoryResponse categoryResponse = updateCategoryService.update(updateCategoryRequest, id);
		return ResponseEntity.ok(categoryResponse);

	}

	@PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryResponse> patch(
			@Valid @RequestBody PatchCategoryRequest patchCategoryRequest,
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
