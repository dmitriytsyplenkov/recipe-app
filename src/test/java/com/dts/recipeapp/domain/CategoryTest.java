package com.dts.recipeapp.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DisplayName("Category class unit tests")
class CategoryTest {

    Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    @DisplayName("Setting id and checking that it is the same as we set")
    void getId() {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    @DisplayName("Testing getDescription method")
    void getDescription() {
    }

    @Test
    @DisplayName("Testing getRecipes method")
    void getRecipes() {
    }
}