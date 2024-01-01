package com.example.campingontop.houseLike.repository;

import com.example.campingontop.houseLike.model.HouseLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseLikeRepository extends JpaRepository<HouseLike, Long> {
    List<HouseLike> findByUserId(Long userId);
}
