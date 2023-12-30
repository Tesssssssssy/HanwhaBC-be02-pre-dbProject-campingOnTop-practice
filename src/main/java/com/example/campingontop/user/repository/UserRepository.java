package com.example.campingontop.user.repository;

import com.example.campingontop.house.model.House;
import com.example.campingontop.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByName(String name);

    @Override
    public Optional<User> findById(Long id);
}
