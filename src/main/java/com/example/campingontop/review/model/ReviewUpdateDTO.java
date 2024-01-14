package com.example.campingontop.review.model;

import org.springframework.web.multipart.MultipartFile;

public class ReviewUpdateDTO {
    private String content;
    private int rating;
    private MultipartFile image; // 이미지 파일 추가

    // Getters and Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}