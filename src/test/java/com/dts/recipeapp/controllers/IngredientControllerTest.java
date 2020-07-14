package com.dts.recipeapp.controllers;

import com.dts.recipeapp.commands.IngredientCommand;
import com.dts.recipeapp.commands.RecipeCommand;
import com.dts.recipeapp.services.IngredientService;
import com.dts.recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

    @Mock
    IngredientService ingredientService;

    @Mock
    RecipeService recipeService;

    @InjectMocks
    IngredientController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        controller = new IngredientController(recipeService, ingredientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    public void testListOfIngredients() throws Exception {
        RecipeCommand command = new RecipeCommand();
        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model()
                        .attributeExists("recipe"));
        verify(recipeService).findCommandById(anyLong());

    }

    @Test
    public void testShowIngredient() throws Exception {

        //given
        IngredientCommand ingredientCommand = new IngredientCommand();

        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        //then
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));

    }
}