package com.songifyDatabase.song.domain.repository;

import com.songifyDatabase.song.domain.model.Song;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface SongRepository extends Repository<Song, Long> {
    Song save(Song song);
    List<Song> findAll();
}
