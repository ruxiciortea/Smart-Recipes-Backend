package com.ruxiciortea.Smart.Recipes.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredient {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private Ingredient ingredientName;

    private int quantity;

    @Enumerated
    private MeasuringUnit measuringUnit;

}
