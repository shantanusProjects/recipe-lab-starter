package com.recipe.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value = 1,message = "rating must be at least 1")
    @Max(value = 5,message = "rating must be at most 5")
    private int rating;

    public Recipe() {}

    public Recipe(String title, String category, int rating) {
        this.title = title;
        this.category = category;
        this.rating = rating;
    }

    // --- getters and setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
}