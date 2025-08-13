package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private List<Song> songs = new ArrayList<>();

    // constructors, getters, setters

    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Song> getSongs() { return songs; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSongs(List<Song> songs) { this.songs = songs; }
}
