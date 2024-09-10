package com.songifyDatabase.domain.crud;

import com.songifyDatabase.domain.crud.dto.AlbumInfo;

import java.time.Instant;

public class SongInfoTestImpl implements AlbumInfo.SongInfo {

    private final Song song;

    public SongInfoTestImpl(final Song song) {
        this.song = song;
    }

    @Override
    public Long getId() {
        return song.getId();
    }

    @Override
    public String getName() {
        return song.getName();
    }

    @Override
    public Instant getReleaseDate() {
        return song.getReleaseDate();
    }

    @Override
    public Long getDuration() {
        return song.getDuration();
    }

    @Override
    public GenreInfo getGenre() {
        return new GenreInfoTestImpl(song.getGenre());
    }
}
