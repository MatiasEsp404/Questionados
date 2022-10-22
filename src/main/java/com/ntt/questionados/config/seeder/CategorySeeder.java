package com.ntt.questionados.config.seeder;

import com.ntt.questionados.entity.CategoryEntity;
import com.ntt.questionados.repository.ICategoryRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CategorySeeder {

  @Autowired
  private ICategoryRepository categoryRepository;

  @EventListener
  public void seed(ContextRefreshedEvent event) {
    createCategoryTable();
  }

  private void createCategoryTable() {
    if (categoryRepository.count() < 13) {
      categoryRepository.saveAll(buildCategoryList());
    }
  }

  private List<CategoryEntity> buildCategoryList() {
    List<CategoryEntity> categoryEntities = new ArrayList<>();
    categoryEntities.add(buildCategory("Geografia"));
    categoryEntities.add(buildCategory("Historia"));
    categoryEntities.add(buildCategory("Arte"));
    categoryEntities.add(buildCategory("Ciencia"));
    categoryEntities.add(buildCategory("Entretenimiento"));
    categoryEntities.add(buildCategory("Deporte"));
    categoryEntities.add(buildCategory("Literatura"));
    categoryEntities.add(buildCategory("Botanica"));
    categoryEntities.add(buildCategory("Geologia"));
    categoryEntities.add(buildCategory("Politica"));
    categoryEntities.add(buildCategory("Economia"));
    categoryEntities.add(buildCategory("Programacion"));
    categoryEntities.add(buildCategory("Medicina"));
    return categoryEntities;
  }

  private CategoryEntity buildCategory(String name) {
    return CategoryEntity.builder()
        .name(name)
        .description("Una pregunta de " + name)
        .build();
  }

}