package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.annotation.Reference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String lyrics;

    @Column(name = "release_year")
    private Integer releaseYear;

    @ManyToMany
    @JoinTable(
            name = "song_authors",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "song_genres",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();

    // constructors, getters, setters

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getLyrics() { return lyrics; }
    public Integer getReleaseYear() { return releaseYear; }
    public List<Author> getAuthors() { return authors; }
    public List<Genre> getGenres() { return genres; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }
    public void setLyrics(String lyrics) { this.lyrics = lyrics; }
    public void setAuthors(List<Author> authors) { this.authors = authors; }
    public void setGenres(List<Genre> genres) { this.genres = genres; }
}

