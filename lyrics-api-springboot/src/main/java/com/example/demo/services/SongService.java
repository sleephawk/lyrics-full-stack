package com.example.demo.services;

import com.example.demo.DTOs.CreateSongRequest;
import com.example.demo.models.Artist;
import com.example.demo.models.Genre;
import com.example.demo.models.Song;
import com.example.demo.repositories.ArtistRepo;
import com.example.demo.repositories.GenreRepo;
import com.example.demo.repositories.SongRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {
    private final SongRepo songRepo;
    private final ArtistRepo artistRepo;
    private final GenreRepo genreRepo;

    public SongService(SongRepo songRepo, ArtistRepo artistRepo, GenreRepo genreRepo) {
        this.songRepo = songRepo;
        this.artistRepo = artistRepo;
        this.genreRepo = genreRepo;
    }

    @Transactional
    public Song createSong(CreateSongRequest request) {
        Song song = new Song();
        song.setName(request.getName());
        song.setLyrics(request.getLyrics());
        song.setReleaseYear(request.getReleaseYear());

        List<Artist> artists = new ArrayList<>();
        if (request.getArtistNames() != null) {
            for (String artistName : request.getArtistNames()) {
                Artist artist = artistRepo.findByName(artistName)
                        .orElseGet(() -> {
                            Artist newArtist = new Artist();
                            newArtist.setName(artistName);
                            return artistRepo.save(newArtist);
                        });
                artists.add(artist);
            }
        }
        song.setArtists(artists);

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

    public Song getRandomSong() {
        long count = songRepo.count();
        if (count == 0) {
            throw new EntityNotFoundException("Repository is empty!");
        }

        int randomId = (int) (Math.random() * count);
        randomId = Math.max(randomId, 1);
        return songRepo.findAll().get(randomId);
    }

    @Transactional
    public void deleteSong(String name) {
        Optional<Song> song = songRepo.findByName(name);
        if(song.isEmpty())
        {
            throw new EntityNotFoundException("Song called: " + name + " does not exist");
        }
        songRepo.deleteById(song.get().getId());
        return;
    }
}
