package com.example.demo.services;

import com.example.demo.repositories.SongRepo;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    private final SongRepo songRepo;

    public SongService(SongRepo songRepo) {
        this.songRepo = songRepo;
    }
}
