package com.ruxiciortea.Smart.Recipes.Repository;

import com.ruxiciortea.Smart.Recipes.Model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredient, Integer> {
}
