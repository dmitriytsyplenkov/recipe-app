package com.dts.recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

    private Long id;
    private Long recipeId;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    private BigDecimal amount;
    private UnitOfMeasureCommand uom;
}
