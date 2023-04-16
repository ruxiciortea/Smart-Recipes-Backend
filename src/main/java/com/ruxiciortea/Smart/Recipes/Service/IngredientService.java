package com.ruxiciortea.Smart.Recipes.Service;

import com.opencsv.CSVReader;
import com.ruxiciortea.Smart.Recipes.Util.DTO.Recipe.IngredientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {

    public List<IngredientDTO> importIngredientsFromCSV() {
        List<String[]> ingredientNames = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader("ingredients.csv"))) {
            List<String[]> r = reader.readAll();
            ingredientNames.addAll(r);
        } catch (Exception exception) {
            System.out.println("Could not read ingredients from CSV");
        }

        List<IngredientDTO> ingredientDTOs = new ArrayList<>();

        for (String[] ingredientName: ingredientNames) {
            String name = ingredientName[0];
            ingredientDTOs.add(new IngredientDTO(name));
        }

        return ingredientDTOs;
    }
}
