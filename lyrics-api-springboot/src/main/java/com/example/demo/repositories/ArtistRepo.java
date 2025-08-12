package com.example.demo.repositories;

import com.example.demo.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepo  extends JpaRepository<Artist, Long> {

}
