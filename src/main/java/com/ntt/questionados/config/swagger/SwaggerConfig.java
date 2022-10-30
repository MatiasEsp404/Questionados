package com.ntt.questionados.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  private static final String TITLE = "Questionados";
  private static final String DESCRIPTION = "Java Talent Camp Intermediate REST Exercise";

  @Bean
  public OpenAPI api() {
    return new OpenAPI().info(new Info().title(TITLE).description(DESCRIPTION));
  }

}
