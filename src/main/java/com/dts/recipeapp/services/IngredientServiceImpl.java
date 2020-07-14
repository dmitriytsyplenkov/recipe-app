package com.dts.recipeapp.services;

import com.dts.recipeapp.commands.IngredientCommand;
import com.dts.recipeapp.converters.IngredientToIngredientCommand;
import com.dts.recipeapp.domain.Ingredient;
import com.dts.recipeapp.domain.Recipe;
import com.dts.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }


    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            //todo implement error handling
            log.error("recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();


        if (!ingredientOptional.isPresent()) {
            //todo implement error handling
            log.error("Ingredient id not found. Id: " + ingredientId);
        }

        return ingredientToIngredientCommand.convert(ingredientOptional.get());


    }
}
