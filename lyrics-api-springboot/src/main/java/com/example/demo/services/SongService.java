package com.example.demo.services;

import com.example.demo.DTOs.CreateSongRequest;
import com.example.demo.models.Author;
import com.example.demo.models.Genre;
import com.example.demo.models.Song;
import com.example.demo.repositories.AuthorRepo;
import com.example.demo.repositories.GenreRepo;
import com.example.demo.repositories.SongRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {
    private final SongRepo songRepo;
    private final AuthorRepo authorRepo;
    private final GenreRepo genreRepo;

    public SongService(SongRepo songRepo, AuthorRepo authorRepo, GenreRepo genreRepo) {
        this.songRepo = songRepo;
        this.authorRepo = authorRepo;
        this.genreRepo = genreRepo;
    }

    @Transactional
    public Song createSong(CreateSongRequest request) {
        Song song = new Song();
        song.setName(request.getName());
        song.setLyrics(request.getLyrics());
        song.setReleaseYear(request.getReleaseYear());

        List<Author> authors = new ArrayList<>();
        if (request.getAuthorNames() != null) {
            for (String authorName : request.getAuthorNames()) {
                Author author = authorRepo.findByName(authorName)
                        .orElseGet(() -> {
                            Author newAuthor = new Author();
                            newAuthor.setName(authorName);
                            return authorRepo.save(newAuthor);
                        });
                authors.add(author);
            }
        }
        song.setAuthors(authors);

        List<Genre> genres = new ArrayList<>();
        if (request.getGenreNames() != null) {
            for (String genreName : request.getGenreNames()) {
                Genre genre = genreRepo.findByName(genreName)
                        .orElseGet(() -> {
                            Genre newGenre = new Genre();
                            newGenre.setName(genreName);
                            return genreRepo.save(newGenre);
                        });
                genres.add(genre);
            }
        }
        song.setGenres(genres);

        return songRepo.save(song);
    }

    public List<Song> songsFromLyrics(List<String> words) {
        if (words == null || words.isEmpty()) {
            return new ArrayList<>();
        }

        List<Song> allSongs = songRepo.findAll();

        return allSongs.stream()
                .filter(song -> {
                    if (song.getLyrics() == null || song.getLyrics().trim().isEmpty()) {
                        return false;
                    }
                    String lowerLyrics = song.getLyrics().toLowerCase();
                    // Check that ALL words are present in the lyrics
                    return words.stream()
                            .map(String::toLowerCase)
                            .allMatch(lowerLyrics::contains);
                })
                .collect(Collectors.toList());
    }
}
