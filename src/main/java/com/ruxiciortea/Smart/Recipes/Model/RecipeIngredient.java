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

    private String ingredientName;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private MeasuringUnit measuringUnit;

}
