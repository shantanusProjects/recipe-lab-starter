package com.recipe.app.controller;

import com.recipe.app.exceptions.ResourceNotFoundException;
import com.recipe.app.model.Recipe;
import com.recipe.app.repository.RecipeRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe savedRecipe = recipeRepository.save(recipe);
        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes(
            @RequestParam(required = false,defaultValue = "NA") String category,
            @RequestParam(required = false) Integer rating ) {
        List<Recipe> recipes = new ArrayList<>();
        if(! category.equals("NA") && rating != null)
            return new ResponseEntity<>(recipeRepository.findByCategoryAndRating(category,rating),HttpStatus.OK);
        else if(!category.equals("NA"))
            return new ResponseEntity<>(recipeRepository.findByCategory(category),HttpStatus.OK);
        else if(rating != null)
            return new ResponseEntity<>(recipeRepository.findByRating(rating),HttpStatus.OK);
        else
            return new ResponseEntity<>(recipeRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        return recipeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}