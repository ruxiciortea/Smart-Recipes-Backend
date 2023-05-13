package com.ruxiciortea.Smart.Recipes.Util.DTO.Recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCreationDTO {

    private String title;

    private String instructionsText;

    private List<RecipeIngredientDTO> ingredients;

}
