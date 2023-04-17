package com.ruxiciortea.Smart.Recipes.Repository;

import com.ruxiciortea.Smart.Recipes.Model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

}
