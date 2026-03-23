package com.moolgranth.backend.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    // --- UPGRADED: Now holds an infinite list of image URLs! ---
    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url", columnDefinition = "LONGTEXT")
    private List<String> images = new ArrayList<>();

    // --- NEW: Tracks if the Admin marked this as a Bestseller ---
    @Column(name = "is_bestseller", columnDefinition = "boolean default false")
    private boolean isBestseller;

    // Default Constructor
    public Product() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }

    // New Getter & Setter for Bestseller
    public boolean getIsBestseller() { return isBestseller; }
    public void setIsBestseller(boolean isBestseller) { this.isBestseller = isBestseller; }
}