package com.songifyDatabase.domain.crud;

import com.songifyDatabase.domain.crud.dto.SongDto;
import com.songifyDatabase.domain.crud.dto.SongLanguageDto;
import com.songifyDatabase.domain.crud.dto.SongRequestDto;
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

    SongDto addSong(final SongRequestDto songDto) {
        SongLanguageDto language = songDto.language();
        SongLanguage songLanguage = SongLanguage.valueOf(language.name());
        Song song = new Song(songDto.name(), songDto.releaseDate(), songDto.duration(), songLanguage);
        log.info("adding new song: " + song);
        Song save = songRepository.save(song);
        return new SongDto(save.getId(), save.getName());
    }
}
