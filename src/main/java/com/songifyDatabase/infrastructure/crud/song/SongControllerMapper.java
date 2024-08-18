package com.songifyDatabase.infrastructure.crud.song;


import com.songifyDatabase.domain.crud.dto.SongDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.request.CreateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.request.UpdateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.response.CreateSongResponseDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.response.DeleteSongResponseDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.response.GetAllSongsResponseDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.response.GetSongResponseDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.response.PartiallyUpdateSongResponseDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.response.UpdateSongResponseDto;
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

//    static SongControllerResponseDto mapFromCreateSongRequestDtoToSongDto(CreateSongRequestDto dto) {
//        return SongControllerResponseDto
//                .builder()
//                .name(dto.songName())
//                .build();
//    }
//
//    static SongControllerResponseDto mapFromUpdateSongRequestDtoToSongDto(UpdateSongRequestDto dto) {
//        return SongControllerResponseDto
//                .builder()
//                .name(dto.songName())
//                .build();
//    }
//
//    static SongControllerResponseDto mapFromPartiallyUpdateSongRequestDtoToSong(PartiallyUpdateSongRequestDto dto) {
//        return SongControllerResponseDto
//                .builder()
//                .name(dto.songName())
//                .build();
//    }
//
//    static CreateSongResponseDto mapFromSongToCreateSongResponseDto(SongDto songDto) {
//        return new CreateSongResponseDto(SongControllerResponseDto
//                .builder()
//                .name(songDto.name())
//                .build());
//    }
//
//    static DeleteSongResponseDto mapFromSongToDeleteSongResponseDto(Long id) {
//        return new DeleteSongResponseDto("You deleted song with id: " + id, HttpStatus.OK);
//    }
//
//    static UpdateSongResponseDto mapFromSongToUpdateSongResponseDto(SongDto newSong) {
//        return new UpdateSongResponseDto(newSong.name(), "testt");
//    }
//
//    static PartiallyUpdateSongResponseDto mapFromSongDtoToPartiallyUpdateSongResponseDto(SongDto songDto) {
//        return new PartiallyUpdateSongResponseDto(SongControllerResponseDto
//                .builder()
//                .name(songDto.name())
//                .build());
//    }
//
//    static GetSongResponseDto mapFromSongToGetSongResponseDto(SongDto songDto) {
//        return new GetSongResponseDto(SongControllerResponseDto
//                .builder()
//                .name(songDto.name())
//                .build());
//    }
//
//    static GetAllSongsResponseDto mapFromSongToGetAllSongsResponseDto(List<SongDto> songs) {
//        List<SongControllerResponseDto> responseDtos = songs.stream()
//                .map(song -> SongControllerResponseDto.builder()
//                        .name(song.name())
//                        .build())
//                .collect(Collectors.toList());
//
//        return new GetAllSongsResponseDto(responseDtos);
//    }
}
