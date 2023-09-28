package com.songifyDatabase.song.domain.service;

import com.songifyDatabase.song.domain.model.Song;
import com.songifyDatabase.song.domain.repository.SongRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Transactional
public class SongAdder {
    private final SongRepository songRepository;

    public SongAdder(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song addSong(Song song) {
        log.info("adding new song: " + song);
        return songRepository.save(song);
    }
}
