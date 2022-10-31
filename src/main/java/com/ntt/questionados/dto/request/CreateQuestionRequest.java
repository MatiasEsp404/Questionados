package com.ntt.questionados.dto.request;

import com.ntt.questionados.config.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.NotEmpty;
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
public class CreateQuestionRequest {

  @NotEmpty(message = "The question must not be null nor empty.")
  @CharactersWithWhiteSpaces(message = "Question must contain only spaces and letters.")
  private String question;

  private Long categoryId;

}
