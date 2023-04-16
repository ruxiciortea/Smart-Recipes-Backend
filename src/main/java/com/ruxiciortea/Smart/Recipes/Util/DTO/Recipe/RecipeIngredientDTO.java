package com.ruxiciortea.Smart.Recipes.Util.DTO.Recipe;

import com.ruxiciortea.Smart.Recipes.Model.Ingredient;
import com.ruxiciortea.Smart.Recipes.Model.MeasuringUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientDTO {

    private String ingredientName;

    private int quantity;

    private MeasuringUnit measuringUnit;

}
