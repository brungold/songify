package com.songifyDatabase.domain.crud;

import com.songifyDatabase.domain.crud.dto.GenreDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Log4j2
public class GenreRetriever {
    private final GenreRepository genreRepository;

    Genre findGenreById(Long genreId) {
        return genreRepository
                .findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException("Genre with id " + genreId + " not found."));
    }

    public Set<GenreDto> findAll() {
        log.info("retrieving all genres: ");
        return genreRepository.findAll()
                .stream()
                .map(genre -> GenreDto.builder()
                        .id(genre.getId())
                        .name(genre.getName())
                        .build()
                )
                .collect(Collectors.toSet());
    }
}
