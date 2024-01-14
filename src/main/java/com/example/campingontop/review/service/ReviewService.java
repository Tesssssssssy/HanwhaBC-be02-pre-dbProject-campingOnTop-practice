package com.example.campingontop.review.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.campingontop.review.model.Review;
import com.example.campingontop.review.model.ReviewDTO;
import com.example.campingontop.review.model.ReviewUpdateDTO;
import com.example.campingontop.review.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private AmazonS3 s3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional
    public Review createReviewFromDTO(ReviewDTO reviewDTO) throws IOException {
        String imageUrl = null;
        if (reviewDTO.getImage() != null && !reviewDTO.getImage().isEmpty()) {
            imageUrl = uploadImageToS3(reviewDTO.getImage());
        }

        Review review = new Review();
        review.setContent(reviewDTO.getContent());
        review.setRating(reviewDTO.getRating());
        review.setImageUrl(imageUrl);
        review.setPostedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    private String uploadImageToS3(MultipartFile image) throws IOException {
        String fileName = "uploaded-images/" + image.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(image.getSize());
        s3.putObject(bucket, fileName, image.getInputStream(), metadata);
        return s3.getUrl(bucket, fileName).toString();
    }
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> getReviewsByNewest() {
        return reviewRepository.findAllByOrderByPostedAtDesc();
    }

    public List<Review> getReviewsByHighestRating() {
        return reviewRepository.findAllByOrderByRatingDesc();
    }
    public Double getAverageRating() {
        return reviewRepository.findAverageRating();
    }
    public List<Review> getReviewsByLowestRating() {
        return reviewRepository.findAllByOrderByRatingAsc();
    }



    @Transactional
    public Review updateReview(Long id, ReviewUpdateDTO reviewUpdateDTO) throws IOException {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Review not found"));

        review.setContent(reviewUpdateDTO.getContent());
        review.setRating(reviewUpdateDTO.getRating());

        // 이미지가 제공된 경우, 이미지 업로드 처리
        if (reviewUpdateDTO.getImage() != null && !reviewUpdateDTO.getImage().isEmpty()) {
            String imageUrl = uploadImageToS3(reviewUpdateDTO.getImage());
            review.setImageUrl(imageUrl);
        }

        return reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}