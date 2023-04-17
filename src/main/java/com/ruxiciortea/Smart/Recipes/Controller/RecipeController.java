package com.ruxiciortea.Smart.Recipes.Controller;

import com.ruxiciortea.Smart.Recipes.Service.RecipeService;
import com.ruxiciortea.Smart.Recipes.Util.DTO.Recipe.RecipeDTO;
import com.ruxiciortea.Smart.Recipes.Util.DTO.Recipe.RecipeIdDTO;
import com.ruxiciortea.Smart.Recipes.Util.DTO.Recipe.RecipeIdentifiedDTO;
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

    @PostMapping()
    @CrossOrigin
    public ResponseEntity<String> add(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                              @RequestBody RecipeDTO recipe) throws Exception {
        return ResponseEntity.ok(recipeService.addRecipe(recipe, auth));
    }

    @PostMapping("/update")
    @CrossOrigin
    public ResponseEntity<String> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                         @RequestBody RecipeIdentifiedDTO recipe) throws Exception {
        return ResponseEntity.ok(recipeService.updateRecipe(recipe, auth));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RecipeIdentifiedDTO>> getAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) throws Exception {
        return ResponseEntity.ok(recipeService.getAllRecipes(auth));
    }

    @GetMapping("/user")
    public ResponseEntity<List<RecipeIdentifiedDTO>> getUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) throws Exception {
        return ResponseEntity.ok(recipeService.getUsersRecipes(auth));
    }

    @DeleteMapping("/delete")
    @CrossOrigin
    public ResponseEntity<Boolean> delete(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                         @RequestBody RecipeIdDTO recipe) throws Exception {
        return ResponseEntity.ok(recipeService.deleteRecipe(recipe, auth));
    }

}
