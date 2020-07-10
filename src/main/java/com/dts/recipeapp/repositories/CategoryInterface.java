package com.dts.recipeapp.repositories;

import com.dts.recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryInterface extends CrudRepository<Category, Long> {
}
