package com.songifyDatabase.domain.crud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GenreRetriever {
    private final GenreRepository genreRepository;

    Genre findGenreById(Long genreId) {
        return genreRepository
                .findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException("Genre with id " + genreId + " not found."));
    }
}
