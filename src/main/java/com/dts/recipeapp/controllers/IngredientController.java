package com.dts.recipeapp.controllers;

import com.dts.recipeapp.commands.IngredientCommand;
import com.dts.recipeapp.commands.RecipeCommand;
import com.dts.recipeapp.commands.UnitOfMeasureCommand;
import com.dts.recipeapp.services.IngredientService;
import com.dts.recipeapp.services.RecipeService;
import com.dts.recipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    public static final String RECIPE_INGREDIENT_INGREDIENTFORM = "recipe/ingredient/ingredientform";
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;


    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;

        this.unitOfMeasureService = unitOfMeasureService;
    }


    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listOfIngredients(@PathVariable String recipeId, Model model) {
        log.debug("Getting ingredient list for recipe id: " + recipeId);
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        log.debug("Getting ingredient with id: " + id + " for recipe with id: " + recipeId);
        model.addAttribute("ingredient", ingredientService
                .findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model) {

        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        //todo check null value of command

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return RECIPE_INGREDIENT_INGREDIENTFORM;
    }


    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id, Model model) {


        model.addAttribute("ingredient", ingredientService
                .findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return RECIPE_INGREDIENT_INGREDIENTFORM;
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String id) {
        log.debug("Deleting ingredient with id: " + id);

        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));

        return "redirect:/recipe/" + recipeId + "/ingredients";


    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@Validated @ModelAttribute("ingredient") IngredientCommand command, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
//            model.addAttribute("ingredient", command);
//
            model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

            bindingResult.getAllErrors().forEach(err -> log.debug(err.toString()));
            return RECIPE_INGREDIENT_INGREDIENTFORM;
        }
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);


        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
}
