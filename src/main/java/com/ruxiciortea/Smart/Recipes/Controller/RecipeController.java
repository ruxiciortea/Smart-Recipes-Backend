package com.ruxiciortea.Smart.Recipes.Controller;

import com.ruxiciortea.Smart.Recipes.Service.RecipeService;
import com.ruxiciortea.Smart.Recipes.Util.DTO.Recipe.RecipeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
