package com.ruxiciortea.Smart.Recipes.Controller;

import com.ruxiciortea.Smart.Recipes.Service.RecipeService;
import com.ruxiciortea.Smart.Recipes.Util.DTO.Recipe.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                              @RequestBody RecipeCreationDTO recipe) throws Exception {
        return ResponseEntity.ok(recipeService.addRecipe(recipe, auth.substring(7)));
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                         @RequestBody RecipeUpdateDTO recipe) throws Exception {
        return ResponseEntity.ok(recipeService.updateRecipe(recipe, auth.substring(7)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RecipeRetrievalDTO>> getAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) throws Exception {
        return ResponseEntity.ok(recipeService.getAllRecipes(auth.substring(7)));
    }

    @GetMapping("/user")
    public ResponseEntity<List<RecipeRetrievalDTO>> getUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) throws Exception {
        return ResponseEntity.ok(recipeService.getUsersRecipes(auth.substring(7)));
    }

    @GetMapping("/ingredient")
    public ResponseEntity<List<RecipeRetrievalDTO>> getWithIngredient(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                                   @RequestBody IngredientDTO ingredient) throws Exception {
        return ResponseEntity.ok(recipeService.getRecipesWithIngredient(ingredient, auth.substring(7)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                         @RequestBody RecipeIdDTO recipe) throws Exception {
        return ResponseEntity.ok(recipeService.deleteRecipe(recipe, auth.substring(7)));
    }

    @GetMapping("/chart_data")
    public ResponseEntity<List<IngredientUsageDTO>> getChartData(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) throws Exception {
        return ResponseEntity.ok(recipeService.getMostUsedIngredientsChard(auth.substring(7)));
    }

}
