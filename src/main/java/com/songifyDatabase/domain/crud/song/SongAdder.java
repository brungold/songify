package com.songifyDatabase.domain.crud.song;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Log4j2
@Service
@Transactional
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class SongAdder {
    private final SongRepository songRepository;


    Song addSong(final Song song, final Long genreId, final SongLanguage songLanguageDatabase) {
        log.info("adding new song: " + song);
        song.setDuration(200L);
        song.setReleaseDate(Instant.now());
        song.setGenreId(genreId);
        song.setLanguage(songLanguageDatabase);
        return songRepository.save(song);
    }
}
