package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // for testing;

    private String name;
    private int releaseYear;
    private String lyrics;

    @OneToMany(mappedBy = "song")
    @JsonIgnore
    private List<Artist> artist;

    @OneToMany(mappedBy = "song")
    @JsonIgnore
    private Genre genre;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getLyrics() {
        return lyrics;
    }

    public List<Artist> getArtist() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
