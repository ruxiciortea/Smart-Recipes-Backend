package com.ruxiciortea.Smart.Recipes.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private String author;

    private String authorEmail;

    private float rating;

    @OneToMany
    private List<RecipeIngredient> ingredients;

}
