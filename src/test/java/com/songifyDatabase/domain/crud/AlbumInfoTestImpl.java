package com.songifyDatabase.domain.crud;

import com.songifyDatabase.domain.crud.dto.AlbumInfo;
import org.checkerframework.checker.units.qual.A;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

class AlbumInfoTestImpl implements AlbumInfo {

    private final Album album;

    public AlbumInfoTestImpl(Album album) {
        this.album = album;
    }

    @Override
    public Long getId() {
        return album.getId();
    }

    @Override
    public String getTitle() {
        return album.getTitle();
    }

    @Override
    public Instant getReleaseDate() {
        return album.getReleaseDate();
    }

    @Override
    public Set<SongInfo> getSongs() {
        return album.getSongs()
                .stream()
                .map(song -> new SongInfoTestImpl(song))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ArtistInfo> getArtists() {
        return album.getArtists()
                .stream()
                .map(artist -> new ArtistInfoTestImpl(artist))
                .collect(Collectors.toSet());
    }
}
