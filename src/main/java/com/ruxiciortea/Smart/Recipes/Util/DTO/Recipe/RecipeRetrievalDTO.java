package com.ruxiciortea.Smart.Recipes.Util.DTO.Recipe;

import com.ruxiciortea.Smart.Recipes.Model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRetrievalDTO {

    private Integer id;

    private String title;

    private String author;

    private String authorEmail;

    private String instructionsText;

    private List<Integer> ratings;

    private List<Comment> comments;

    private List<RecipeIngredientDTO> ingredients;

}