package com.songifyDatabase.song.domain.service;

import com.songifyDatabase.song.domain.model.Song;
import com.songifyDatabase.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class SongRetriever {
    private final SongRepository songRepository;

    public SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findAll() {
        log.info("retrieving all sons: ");
        return songRepository.findAll();
    }
    public List<Song> findAllLimitedBy(Integer limit) {
        return songRepository.findAll()
                .stream().limit(limit)
                .toList();
    }

    public Optional<Song> findSongById(Integer id) {
        return songRepository.findById(id);
    }
}
