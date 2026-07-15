package com.recipe.app.repository;

import com.recipe.app.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByCategoryAndRating(String category, int rating);

    List<Recipe> findByCategory(String category);

    List<Recipe> findByRating(int rating);
}