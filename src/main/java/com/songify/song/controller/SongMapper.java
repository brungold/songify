package com.songify.song.controller;

import com.songify.song.dto.request.CreateSongRequestDto;
//Tzw klasa utilowa (narzędziowa), nie mam mieć żadnej logiki tylko robić prostą
//logikę przepakowania z jednego obiektu na drugi
public class SongMapper {

    public static Song mapFromCreateSongRequestDtoToSong(CreateSongRequestDto dto) {
        return new Song(dto.songName(), dto.artist());
    }
}
