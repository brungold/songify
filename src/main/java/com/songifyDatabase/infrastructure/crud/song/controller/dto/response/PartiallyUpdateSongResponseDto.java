package com.songifyDatabase.infrastructure.crud.song.controller.dto.response;

import com.songifyDatabase.domain.crud.dto.SongDto;

public record PartiallyUpdateSongResponseDto(SongDto updatedSong) {
}
