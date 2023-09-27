package com.songifyDatabase.song.domain.service;

import com.songifyDatabase.song.domain.model.Song;
import com.songifyDatabase.song.domain.repository.SongRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
public class SongUpdater {
    private final SongRepository songRepository;

    public SongUpdater(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void updateById(Long id, Song newSong) {
        songRepository.updateById(id, newSong);
    }
}
