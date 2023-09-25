package com.songifyDatabase.song.domain.repository;

import com.songifyDatabase.song.domain.model.Song;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class SongRepositoryInMemory implements SongRepository {
    Map<Integer, Song> database = new HashMap<>(Map.of(
            1, new Song("song1", "Shawn Mendes"),
            2, new Song("One", "Metallica"),
            3, new Song("Fire", "Jimi Hendrix"),
            4, new Song("Go!", "Barnaba")
    ));
    @Override
    public Song save(Song song) {
        database.put(database.size() + 1, song);
        return song;
    }
    @Override
    public List<Song> findAll() {
        return database.values()
                .stream()
                .toList();
    }
}
