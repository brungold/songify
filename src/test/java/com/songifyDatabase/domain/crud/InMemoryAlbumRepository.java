package com.songifyDatabase.domain.crud;

import com.songifyDatabase.domain.crud.dto.AlbumInfo;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class InMemoryAlbumRepository implements AlbumRepository {

    Map<Long, Album> db = new HashMap<>();
    AtomicInteger index = new AtomicInteger(0);

    @Override
    public Optional<Album> findById(final Long albumId) {
        Album album = db.get(albumId);
        return Optional.ofNullable(album);
    }

    @Override
    public int deleteByIdIn(final Collection<Long> ids) {
        ids.forEach(
                id -> db.remove(id)
        );
        return 0;
    }

    @Override
    public Album save(final Album album) {
        long index = this.index.getAndIncrement();
        db.put(index, album);
        album.setId(index);
        return album;
    }

    @Override
    public Optional<AlbumInfo> findAlbumByIdWithSongsAndArtists(Long id) {
        return Optional.empty();
    }

    @Override
    public Set<Album> findAllAlbumsByArtistId(final Long id) {
        return db.values().stream()
                .filter(album -> album.getArtists().stream()
                        .anyMatch(artist -> artist.getId().equals(id)))
                .collect(Collectors.toSet());
    }
}
