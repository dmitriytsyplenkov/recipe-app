package com.dts.recipeapp.controllers;

import com.dts.recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@DisplayName("IndexController unit tests with mockito")
class IndexControllerTest {

    IndexController indexController;
    @Mock
    Model model;
    @Mock
    RecipeService recipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    @DisplayName("Checking correctness of getting index")
    void getIndexPage() {

        assertEquals("index",indexController.getIndexPage(model));
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());

    }
}