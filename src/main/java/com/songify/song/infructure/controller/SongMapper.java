package com.songify.song.infructure.controller;

import com.songify.song.infructure.controller.dto.request.CreateSongRequestDto;
import com.songify.song.infructure.controller.dto.response.CreateSongResponseDto;
import com.songify.song.domain.model.Song;
import com.songify.song.infructure.controller.dto.response.DeleteSongResponseDto;
import com.songify.song.infructure.controller.dto.response.GetSongResponseDto;
import org.springframework.http.HttpStatus;

//Tzw klasa utilowa (narzędziowa), nie mam mieć żadnej logiki tylko robić prostą
//logikę przepakowania z jednego obiektu na drugi
public class SongMapper {

    public static Song mapFromCreateSongRequestDtoToSong(CreateSongRequestDto dto) {
        return new Song(dto.songName(), dto.artist());
    }

    public static CreateSongResponseDto mapFromSongToCreateSongResponseDto(Song song) {
        return new CreateSongResponseDto(song);
    }

    public static GetSongResponseDto mapFromSongToGetSongResponseDto(Song song) {
        return new GetSongResponseDto(song);
    }
    public static DeleteSongResponseDto mapFromSongToDeleteSongResponseDto(Integer id) {
        return new DeleteSongResponseDto("you deleted song with id: " + id, HttpStatus.OK);
    }
}
