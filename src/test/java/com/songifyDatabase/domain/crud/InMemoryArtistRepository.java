package com.songifyDatabase.domain.crud;

import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

class InMemoryArtistRepository implements ArtistRepository{
    @Override
    public int deleteById(final Long id) {
        return 0;
    }

    @Override
    public Artist save(final Artist artist) {
        return null;
    }

    @Override
    public Set<Artist> findAll(final Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Artist> findById(final Long artistId) {
        return Optional.empty();
    }
}
