package com.ruxiciortea.Smart.Recipes.Service;

import com.ruxiciortea.Smart.Recipes.Config.JwtService;
import com.ruxiciortea.Smart.Recipes.Model.*;
import com.ruxiciortea.Smart.Recipes.Repository.RecipeIngredientRepository;
import com.ruxiciortea.Smart.Recipes.Repository.RecipeRepository;
import com.ruxiciortea.Smart.Recipes.Repository.UserRepository;
import com.ruxiciortea.Smart.Recipes.Util.DTO.Recipe.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository ingredientsRepository;
    private final UserRepository userRepository;

    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    public String addRecipe(RecipeCreationDTO recipe, String auth) throws Exception {
        String userEmail = jwtService.extractUsername(auth);
        Optional<User> databaseUser = userRepository.findByEmail(userEmail);

        if (databaseUser.isPresent()) {
            User user = databaseUser.get();

            Recipe completeRecipe = Recipe.builder()
                    .title(recipe.getTitle())
                    .author(user.getFirstname() + " " + user.getLastname())
                    .authorEmail(user.getEmail())
                    .instructionsText(recipe.getInstructionsText())
                    .ingredients(getIngredientList(recipe.getIngredients()))
                    .build();

            recipeRepository.save(completeRecipe);
            return "Ok";
        }

        throw new UsernameNotFoundException("Could not find user in database.");
    }

    public String updateRecipe(RecipeUpdateDTO recipe, String auth) throws Exception {
        String userEmail = jwtService.extractUsername(auth);
        Optional<User> databaseUser = userRepository.findByEmail(userEmail);
        Optional<Recipe> databaseRecipe = recipeRepository.findById(recipe.getId());

        if (databaseUser.isPresent() && databaseRecipe.isPresent()) {
            User user = databaseUser.get();
            Recipe oldRecipe = databaseRecipe.get();

            if (user.getRole().equals(Role.USER) && !user.getEmail().equals(oldRecipe.getAuthorEmail())) {
                throw new ChangeSetPersister.NotFoundException();
            }

            recipeRepository.delete(oldRecipe);

            oldRecipe.setTitle(recipe.getTitle());
            oldRecipe.setInstructionsText(recipe.getInstructionsText());
            oldRecipe.setIngredients(getIngredientList(recipe.getIngredients()));

            recipeRepository.save(oldRecipe);

            return "Ok";
        }

        throw new ChangeSetPersister.NotFoundException();
    }

    public boolean deleteRecipe(RecipeIdDTO recipe, String auth) throws Exception {
        String userEmail = jwtService.extractUsername(auth);
        Optional<User> databaseUser = userRepository.findByEmail(userEmail);
        Optional<Recipe> databaseRecipe = recipeRepository.findById(recipe.getId());

        if (databaseUser.isPresent() && databaseRecipe.isPresent()) {
            User user = databaseUser.get();
            Recipe oldRecipe = databaseRecipe.get();

            if (user.getRole().equals(Role.USER) && !user.getEmail().equals(oldRecipe.getAuthorEmail())) {
                throw new ChangeSetPersister.NotFoundException();
            }

            recipeRepository.delete(oldRecipe);

            return true;
        }

        throw new ChangeSetPersister.NotFoundException();
    }

    public List<RecipeRetrievalDTO> getAllRecipes(String auth) {
        String userEmail = jwtService.extractUsername(auth);
        Optional<User> databaseUser = userRepository.findByEmail(userEmail);

        if (databaseUser.isPresent()) {
            List<Recipe> allRecipes = getAllRecipesFromDB();
            return getRecipesDTO(allRecipes);
        }

        throw new UsernameNotFoundException("");
    }

    public List<RecipeRetrievalDTO> getUsersRecipes(String auth) {
        String userEmail = jwtService.extractUsername(auth);
        Optional<User> databaseUser = userRepository.findByEmail(userEmail);

        if (databaseUser.isPresent()) {
            List<Recipe> allRecipes = getAllRecipesFromDB();
            List<RecipeRetrievalDTO> usersRecipes = new ArrayList<>();

            for (Recipe recipe: allRecipes) {
                if (recipe.getAuthorEmail().equals(userEmail)) {
                    RecipeRetrievalDTO recipeDTO = modelMapper.map(recipe, RecipeRetrievalDTO.class);
                    usersRecipes.add(recipeDTO);
                }
            }

            return usersRecipes;
        }

        throw new UsernameNotFoundException("");
    }

    public List<RecipeRetrievalDTO> getRecipesWithIngredient(IngredientDTO ingredient, String auth) throws Exception {
        String userEmail = jwtService.extractUsername(auth);
        Optional<User> databaseUser = userRepository.findByEmail(userEmail);

        if (databaseUser.isPresent()) {
            List<Recipe> allRecipes = getAllRecipesFromDB();
            List<RecipeRetrievalDTO> ingredientRecipes = new ArrayList<>();

            for (Recipe recipe: allRecipes) {
                for (RecipeIngredient recipeIngredient: recipe.getIngredients()) {
                    if (recipeIngredient.getIngredientName().equals(ingredient.getName())) {
                        RecipeRetrievalDTO recipeDTO = modelMapper.map(recipe, RecipeRetrievalDTO.class);
                        ingredientRecipes.add(recipeDTO);
                    }
                }
            }

            return ingredientRecipes;
        }

        throw new UsernameNotFoundException("");
    }

    public List<IngredientUsageDTO> getMostUsedIngredientsChard(String auth) throws Exception {
        String userEmail = jwtService.extractUsername(auth);
        Optional<User> databaseUser = userRepository.findByEmail(userEmail);

        if (databaseUser.isPresent()) {
            List<Recipe> allRecipes = getAllRecipesFromDB();

            return getIngredientUsageData(allRecipes);
        }

        throw new UsernameNotFoundException("");
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

    private List<Recipe> getAllRecipesFromDB() {
        return recipeRepository.findAll();
    }

    private List<RecipeRetrievalDTO> getRecipesDTO(List<Recipe> recipes) {
        List<RecipeRetrievalDTO> recipeDTOs = new ArrayList<>();

        for (Recipe recipe: recipes) {
            RecipeRetrievalDTO recipeDTO = modelMapper.map(recipe, RecipeRetrievalDTO.class);
            recipeDTOs.add(recipeDTO);
        }

        return recipeDTOs;
    }

    private List<IngredientUsageDTO> getIngredientUsageData(List<Recipe> recipes) {
        List<IngredientUsageDTO> ingredientUsage = new ArrayList<>();

        for (Recipe recipe: recipes) {
            for (RecipeIngredient ingredient: recipe.getIngredients()) {
                if (containsIngredient(ingredientUsage, ingredient.getIngredientName())) {
                    countIngredient(ingredientUsage, ingredient.getIngredientName());
                } else {
                    ingredientUsage.add(new IngredientUsageDTO(ingredient.getIngredientName(), 1));
                }
            }
        }

        return ingredientUsage;
    }

    private boolean containsIngredient(List<IngredientUsageDTO> ingredientUsageDTOs, String ingredientName) {
        for (IngredientUsageDTO ingredientUsage: ingredientUsageDTOs) {
            if (ingredientUsage.getIngredientName().equals(ingredientName)) {
                return true;
            }
        }

        return false;
    }

    private void countIngredient(List<IngredientUsageDTO> ingredientUsageDTOs, String ingredientName) {
        for (IngredientUsageDTO ingredientUsage: ingredientUsageDTOs) {
            if (ingredientUsage.getIngredientName().equals(ingredientName)) {
                ingredientUsage.setNumberUses(ingredientUsage.getNumberUses() + 1);
            }
        }
    }

}
