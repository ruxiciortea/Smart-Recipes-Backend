package com.ruxiciortea.Smart.Recipes.Util.DTO.Recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientUsageDTO {

    private String ingredientName;

    private int numberUses;

}
