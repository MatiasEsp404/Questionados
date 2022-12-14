package com.ntt.questionados.dto.response;

import java.util.List;
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
public class NextQuestionResponse {

  private QuestionResponse question;
  private List<ResponseResponse> responses;
}
