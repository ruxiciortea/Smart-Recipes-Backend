package com.ruxiciortea.Smart.Recipes.Controller;

import com.ruxiciortea.Smart.Recipes.Service.IngredientService;
import com.ruxiciortea.Smart.Recipes.Util.DTO.Recipe.IngredientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/import")
    @CrossOrigin
    public ResponseEntity<List<IngredientDTO>> importIngredients() {
        return ResponseEntity.ok(ingredientService.importIngredientsFromCSV());
    }

}
