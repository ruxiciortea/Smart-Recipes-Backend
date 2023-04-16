package com.ruxiciortea.Smart.Recipes.Repository;

import com.ruxiciortea.Smart.Recipes.Model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {
}
