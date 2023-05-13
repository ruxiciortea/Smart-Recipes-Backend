package com.ruxiciortea.Smart.Recipes.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    private String instructionsText;

    @ElementCollection
    @Builder.Default
    private List<Integer> ratings = new ArrayList<>();

    @OneToMany
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @OneToMany
    private List<RecipeIngredient> ingredients;

    public void addRating(int rating) {
        ratings.add(rating);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

}
