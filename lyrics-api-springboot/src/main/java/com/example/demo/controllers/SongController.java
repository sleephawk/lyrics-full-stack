package com.example.demo.controllers;


import com.example.demo.services.ArtistService;
import com.example.demo.services.SongService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    public final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }
}

