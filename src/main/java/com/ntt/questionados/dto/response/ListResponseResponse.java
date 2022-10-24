package com.ntt.questionados.dto.response;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListResponseResponse {

  private Set<ResponseResponse> responses;

}
