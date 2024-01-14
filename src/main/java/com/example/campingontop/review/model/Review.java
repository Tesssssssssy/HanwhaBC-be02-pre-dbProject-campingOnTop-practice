package com.example.campingontop.review.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    private String content;
    private int rating; // 1-5 별점
    private LocalDateTime postedAt;

    // Default Constructor
    public Review() {}

    // Getters
    public Long getId() { return id; }
    public String getContent() { return content; }
    public int getRating() { return rating; }
    public LocalDateTime getPostedAt() { return postedAt; }
    public String getImageUrl() { return imageUrl; }
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setContent(String content) { this.content = content; }
    public void setRating(int rating) { this.rating = rating; }
    public void setPostedAt(LocalDateTime postedAt) { this.postedAt = postedAt; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

}