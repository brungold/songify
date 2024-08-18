package com.songifyDatabase.domain.crud;

import com.songifyDatabase.infrastructure.crud.song.controller.dto.request.CreateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.request.UpdateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.response.SongControllerResponseDto;

class SongDomainMapper {
    public static SongControllerResponseDto mapFromSongToSongDto(Song song) {
        return new SongControllerResponseDto(song.getId(), song.getName());
    }
    public static Song mapFromCreateSongRequestDtoToSong(CreateSongRequestDto dto) {
        return new Song(dto.songName());
    }
    public static Song mapFromUpdateSongRequestDtoToSong(UpdateSongRequestDto dto) {
        return new Song(dto.songName());
    }

    public static Song mapFromPartiallyUpdateSongRequestDtoToSong(PartiallyUpdateSongRequestDto dto) {
        return new Song(dto.songName());
    }
}
