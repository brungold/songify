package com.songifyDatabase.infrastructure.crud.controller;


import com.songifyDatabase.domain.crud.song.dto.SongDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.request.CreateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.request.UpdateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.response.CreateSongResponseDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.response.DeleteSongResponseDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.response.GetAllSongsResponseDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.response.GetSongResponseDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.response.PartiallyUpdateSongResponseDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.response.UpdateSongResponseDto;
import org.springframework.http.HttpStatus;

import java.util.List;


public class SongControllerMapper {


    static SongDto mapFromCreateSongRequestDtoToSongDto(CreateSongRequestDto dto) {
        return SongDto
                .builder()
                .name(dto.songName())
                .build();
    }

    static SongDto mapFromUpdateSongRequestDtoToSongDto(UpdateSongRequestDto dto) {
        return SongDto
                .builder()
                .name(dto.songName())
                .build();
    }

    static SongDto mapFromPartiallyUpdateSongRequestDtoToSong(PartiallyUpdateSongRequestDto dto) {
        return SongDto
                .builder()
                .name(dto.songName())
                .build();
    }

    static CreateSongResponseDto mapFromSongToCreateSongResponseDto(SongDto songDto) {
        return new CreateSongResponseDto(songDto);
    }

    static DeleteSongResponseDto mapFromSongToDeleteSongResponseDto(Long id) {
        return new DeleteSongResponseDto("You deleted song with id: " + id, HttpStatus.OK);
    }

    static UpdateSongResponseDto mapFromSongToUpdateSongResponseDto(SongDto newSong) {
        return new UpdateSongResponseDto(newSong.name(), "testt");
    }

    static PartiallyUpdateSongResponseDto mapFromSongDtoToPartiallyUpdateSongResponseDto(SongDto songDto) {
        return new PartiallyUpdateSongResponseDto(songDto);
    }

    static GetSongResponseDto mapFromSongToGetSongResponseDto(SongDto songDto) {
        return new GetSongResponseDto(songDto);
    }

    static GetAllSongsResponseDto mapFromSongToGetAllSongsResponseDto(List<SongDto> songs) {
        return new GetAllSongsResponseDto(songs);
    }
}
