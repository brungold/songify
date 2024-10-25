package com.songifyDatabase.domain.crud;

import com.songifyDatabase.domain.crud.dto.GenreDto;
import com.songifyDatabase.domain.crud.dto.SongDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class SongRetriever {
    private final SongRepository songRepository;


    List<SongDto> findAll(Pageable pageable) {
        log.info("retrieving all songs: ");
        return songRepository.findAll(pageable)
                .stream()
                .map(song -> SongDto.builder()
                        .id(song.getId())
                        .name(song.getName())
                        .genre(new GenreDto(song.getGenre().getId(), song.getGenre().getName()))
                        .build())
                .toList();
    }

    Song findSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException("Song with id: " + id + " not found"));
    }

    SongDto findSongDtoById(Long id) {
        return songRepository.findById(id)
                .map(song -> SongDto.builder()
                        .id(song.getId())
                        .name(song.getName())
                        .genre(new GenreDto(
                                song.getGenre().getId(),
                                song.getGenre().getName()
                        ))
                        .build())
                .orElseThrow(() -> new SongNotFoundException("Song with id: " + id + " not found"));
    }

    void existsById(Long id) {
        if (!songRepository.existsById(id)) {
            throw new SongNotFoundException("Song with id: " + id + " not found");
        }
    }
}
