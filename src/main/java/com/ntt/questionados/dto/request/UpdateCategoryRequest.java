package com.ntt.questionados.dto.request;

import javax.validation.constraints.NotEmpty;

import com.ntt.questionados.dto.request.validation.CharactersWithWhiteSpaces;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCategoryRequest {

	@NotEmpty(message = "The name must not be null nor empty.")
	@CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
	private String name;
	private String description;
	
}
