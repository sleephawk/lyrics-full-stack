package com.example.demo.repositories;

import com.example.demo.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Long> {
    Optional<Artist> findByName(String name);
}
