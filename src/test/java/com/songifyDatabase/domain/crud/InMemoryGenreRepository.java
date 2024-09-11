package com.songifyDatabase.domain.crud;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class InMemoryGenreRepository implements GenreRepository{

    Map<Long, Genre> db = new HashMap<>();
    AtomicInteger index = new AtomicInteger(1);

    public InMemoryGenreRepository() {
        save(new Genre(1L, "default"));
    }

    @Override
    public int deleteById(final Long id) {
        return 0;
    }

    @Override
    public Genre save(final Genre genre) {
        long index = this.index.getAndIncrement();
        db.put(index, genre);
        genre.setId(index);
        return genre;
    }
}
