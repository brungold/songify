package com.songifyDatabase.infrastructure.crud.controller.dto.response;

import com.songifyDatabase.domain.crud.dto.SongDto;

public record PartiallyUpdateSongResponseDto(SongDto updatedSong) {
}
