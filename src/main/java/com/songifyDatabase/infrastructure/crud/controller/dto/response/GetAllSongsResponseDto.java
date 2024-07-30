package com.songifyDatabase.infrastructure.crud.controller.dto.response;

import com.songifyDatabase.domain.crud.song.dto.SongDto;

import java.util.List;

public record GetAllSongsResponseDto(List<SongDto> songs) {
}
