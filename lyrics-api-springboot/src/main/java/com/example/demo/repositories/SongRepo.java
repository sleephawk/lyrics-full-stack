package com.example.demo.repositories;

import com.example.demo.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepo extends JpaRepository<Song, Long> {
    Optional<Song> findByName(String name);
}
