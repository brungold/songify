package com.songifyDatabase.song.domain.service;

import com.songifyDatabase.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SongDeleter {
    private final SongRepository songRepository;
    private final SongRetriever songRetriever;

    public SongDeleter(SongRepository songRepository, SongRetriever songRetriever) {
        this.songRepository = songRepository;
        this.songRetriever = songRetriever;
    }

    public void deleteById(Long id) {
        songRetriever.findSongById(id);
        log.info("deleting song by id: " + id);
        songRepository.deleteById(id);
    }
}
