package com.example.demo.repositories;

import com.example.demo.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepo extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
}
