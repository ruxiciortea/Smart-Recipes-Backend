package com.ruxiciortea.Smart.Recipes.Service;

import com.ruxiciortea.Smart.Recipes.Config.JwtService;
import com.ruxiciortea.Smart.Recipes.Model.Ingredient;
import com.ruxiciortea.Smart.Recipes.Model.Recipe;
import com.ruxiciortea.Smart.Recipes.Model.RecipeIngredient;
import com.ruxiciortea.Smart.Recipes.Model.User;
import com.ruxiciortea.Smart.Recipes.Repository.IngredientsRepository;
import com.ruxiciortea.Smart.Recipes.Repository.RecipeIngredientRepository;
import com.ruxiciortea.Smart.Recipes.Repository.RecipeRepository;
import com.ruxiciortea.Smart.Recipes.Repository.UserRepository;
import com.ruxiciortea.Smart.Recipes.Util.DTO.Recipe.RecipeDTO;
import com.ruxiciortea.Smart.Recipes.Util.DTO.Recipe.RecipeIngredientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository ingredientsRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public String addRecipe(RecipeDTO recipe, String auth) throws Exception {
        String userEmail = jwtService.extractUsername(auth.substring(7));
        Optional<User> databaseUser = userRepository.findByEmail(userEmail);

        if (databaseUser.isPresent()) {
            User user = databaseUser.get();

            List<RecipeIngredient> ingredients = getIngredientList(recipe.getIngredients());

            Recipe completeRecipe = Recipe.builder()
                    .title(recipe.getTitle())
                    .author(user.getFirstname() + " " + user.getLastname())
                    .authorEmail(user.getEmail())
                    .rating(0.0f)
                    .ingredients(ingredients)
                    .build();

            recipeRepository.save(completeRecipe);
            return "Ok";
        }

        throw new UsernameNotFoundException("Could not find user in database.");
    }

    private List<RecipeIngredient> getIngredientList(List<RecipeIngredientDTO> ingredients) {
        List<RecipeIngredient> ingredientList = new ArrayList<>();

        for (RecipeIngredientDTO ingredientDTO: ingredients) {
            RecipeIngredient newIngredient = RecipeIngredient.builder()
                    .ingredientName(ingredientDTO.getIngredientName())
                    .quantity(ingredientDTO.getQuantity())
                    .measuringUnit(ingredientDTO.getMeasuringUnit())
                    .build();
            ingredientList.add(newIngredient);
            ingredientsRepository.save(newIngredient);
        }

        return ingredientList;
    }

}
