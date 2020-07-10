package com.dts.recipeapp.controllers;

import com.dts.recipeapp.domain.Category;
import com.dts.recipeapp.domain.UnitOfMeasure;
import com.dts.recipeapp.repositories.CategoryRepository;
import com.dts.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @RequestMapping({"","/","/index"})
    public String getIndexPage() {

        Optional<Category> category = categoryRepository.findByDescription("Italian");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category id is: " + category.get().getId());
        System.out.println("UOM id is: " + unitOfMeasure.get().getId());


        return "index";
    }

}
