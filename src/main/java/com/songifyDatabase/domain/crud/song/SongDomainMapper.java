package com.songifyDatabase.domain.crud.song;

import com.songifyDatabase.infrastructure.crud.controller.dto.request.CreateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.request.UpdateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.response.SongControllerResponseDto;

class SongDomainMapper {
    public static SongControllerResponseDto mapFromSongToSongDto(Song song) {
        return new SongControllerResponseDto(song.getId(), song.getName(), song.getArtist());
    }
    public static Song mapFromCreateSongRequestDtoToSong(CreateSongRequestDto dto) {
        return new Song(dto.songName(), dto.artist());
    }
    public static Song mapFromUpdateSongRequestDtoToSong(UpdateSongRequestDto dto) {
        return new Song(dto.songName(), dto.artist());
    }

    public static Song mapFromPartiallyUpdateSongRequestDtoToSong(PartiallyUpdateSongRequestDto dto) {
        return new Song(dto.songName(), dto.artist());
    }
}
