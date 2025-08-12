package com.example.demo.services;

import com.example.demo.repositories.ArtistRepo;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {
    private final ArtistRepo artistRepo;

    public ArtistService(ArtistRepo artistRepo) {
        this.artistRepo = artistRepo;
    }
}
