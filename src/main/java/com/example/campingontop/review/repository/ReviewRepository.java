package com.example.campingontop.review.repository;
import com.example.campingontop.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByOrderByPostedAtDesc(); // 최신 순 조회
    List<Review> findAllByOrderByRatingDesc(); // 별점 높은 순 조회
    List<Review> findAllByOrderByRatingAsc(); // 별점 낮은 순 조회
    @Query("SELECT AVG(r.rating) FROM Review r")
    Double findAverageRating();
}