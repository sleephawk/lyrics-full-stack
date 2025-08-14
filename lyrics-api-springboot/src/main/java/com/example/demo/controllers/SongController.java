package com.example.demo.controllers;


import com.example.demo.DTOs.CreateSongRequest;
import com.example.demo.models.Song;
import com.example.demo.services.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public ResponseEntity<Song> createSong(@RequestBody CreateSongRequest request) {
        try {
            Song createdSong = songService.createSong(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSong);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Song>> songsFromLyrics(@RequestParam List<String> words) {
        try {
            List<Song> songs = songService.songsFromLyrics(words);
            return ResponseEntity.ok(songs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/randomSong")
    public Song getRandomSong() {
        return songService.getRandomSong();
    }

    @DeleteMapping("/{name}")
    public String deleteSong(@PathVariable String name) {
        songService.deleteSong(name);
        return "Song called: " + name + " has been deleted";
    }
}

