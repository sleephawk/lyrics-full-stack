package com.example.demo.controllers;

import com.example.demo.services.ArtistService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    public final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }
}
