package com.example.demo.DTOs;

import java.util.List;

public class CreateSongRequest {
    private String name;
    private String lyrics;
    private Integer releaseYear;
    private List<String> authorNames;
    private List<String> genreNames;

    // Constructors
    public CreateSongRequest() {}

    public CreateSongRequest(String name, String lyrics, Integer releaseYear,
                             List<String> authorNames, List<String> genreNames) {
        this.name = name;
        this.lyrics = lyrics;
        this.releaseYear = releaseYear;
        this.authorNames = authorNames;
        this.genreNames = genreNames;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLyrics() { return lyrics; }
    public void setLyrics(String lyrics) { this.lyrics = lyrics; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public List<String> getAuthorNames() { return authorNames; }
    public void setAuthorNames(List<String> authorNames) { this.authorNames = authorNames; }

    public List<String> getGenreNames() { return genreNames; }
    public void setGenreNames(List<String> genreNames) { this.genreNames = genreNames; }
}
