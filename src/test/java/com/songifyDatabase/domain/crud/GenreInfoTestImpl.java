package com.songifyDatabase.domain.crud;

import com.songifyDatabase.domain.crud.dto.AlbumInfo;

public class GenreInfoTestImpl implements AlbumInfo.SongInfo.GenreInfo {

    private final Genre genre;

    public GenreInfoTestImpl(Genre genre) {
        this.genre = genre;
    }


    @Override
    public Long getId() {
        return genre.getId();
    }

    @Override
    public String getName() {
        return genre.getName();
    }
}
