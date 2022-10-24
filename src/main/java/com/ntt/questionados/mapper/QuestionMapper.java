package com.ntt.questionados.mapper;

import com.ntt.questionados.config.exception.runtime.EntityNotFoundException;
import com.ntt.questionados.dto.request.CreateQuestionRequest;
import com.ntt.questionados.dto.response.QuestionResponse;
import com.ntt.questionados.entity.CategoryEntity;
import com.ntt.questionados.entity.QuestionEntity;
import com.ntt.questionados.repository.ICategoryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

  @Autowired
  private CategoryMapper categoryMapper;

  @Autowired
  private ICategoryRepository categoryRepository;

  public QuestionResponse toQuestionResponse(QuestionEntity entity) {
    return QuestionResponse.builder()
        .id(entity.getId())
        .question(entity.getQuestion())
        .category(categoryMapper.toCategoryResponse(entity.getCategoryEntity()))
        .build();
  }

  public QuestionEntity toQuestionEntity(CreateQuestionRequest request) {
    return QuestionEntity.builder()
        .question(request.getQuestion())
        .categoryEntity(findCategory(request.getCategoryId()))
        .build();
  }

  private CategoryEntity findCategory(Long id) {
    Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
    if (categoryEntity.isEmpty()) {
      throw new EntityNotFoundException("Category not found.");
    }
    return categoryEntity.get();
  }
}
