package com.songifyDatabase.domain.crud;

import com.songifyDatabase.domain.crud.dto.AlbumInfo;

public class ArtistInfoTestImpl implements AlbumInfo.ArtistInfo {

    private final Artist artist;

    public ArtistInfoTestImpl(final Artist artist) {
        this.artist = artist;
    }

    @Override
    public Long getId() {
        return artist.getId();
    }

    @Override
    public String getName() {
        return artist.getName();
    }
}
