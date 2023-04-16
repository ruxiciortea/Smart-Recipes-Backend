package com.ruxiciortea.Smart.Recipes.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String name;

    public Ingredient(String name) {
        this.name = name;
    }
}
