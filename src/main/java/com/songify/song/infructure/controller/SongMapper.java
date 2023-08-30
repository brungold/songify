package com.songify.song.infructure.controller;

import com.songify.song.infructure.controller.dto.request.CreateSongRequestDto;
import com.songify.song.infructure.controller.dto.response.CreateSongResponseDto;
import com.songify.song.domain.model.Song;

//Tzw klasa utilowa (narzędziowa), nie mam mieć żadnej logiki tylko robić prostą
//logikę przepakowania z jednego obiektu na drugi
public class SongMapper {

    public static Song mapFromCreateSongRequestDtoToSong(CreateSongRequestDto dto) {
        return new Song(dto.songName(), dto.artist());
    }

    public static CreateSongResponseDto mapFromSongToCreateSongResponseDto(Song song) {
        return new CreateSongResponseDto(song);
    }
}
