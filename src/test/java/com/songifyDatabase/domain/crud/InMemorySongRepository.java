package com.songifyDatabase.domain.crud;

import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class InMemorySongRepository implements SongRepository{

    Map<Long, Song> db = new HashMap<>();
    AtomicInteger index = new AtomicInteger(0);

    @Override
    public int deleteByIdIn(final Collection<Long> ids) {
        ids.forEach(
                id -> db.remove(id)
        );
        return 0;
    }

    @Override
    public List<Song> findAll(final Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Song> findById(final Long id) {
        Song song = db.get(id);
        return Optional.ofNullable(song);
    }

    @Override
    public void deleteById(final Long id) {

    }

    @Override
    public void updateById(final Long id, final Song newSong) {

    }

    @Override
    public Song save(final Song song) {
        long index = this.index.getAndIncrement();
        db.put(index, song);
        song.setId(index);
        song.setGenre(new Genre(1L, "default"));
        return song;
    }

    @Override
    public boolean existsById(final Long id) {
        return false;
    }
}
