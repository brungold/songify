package com.songifyDatabase.infrastructure.crud.controller.dto.response;

import com.songifyDatabase.domain.crud.song.dto.SongDto;

public record PartiallyUpdateSongResponseDto(SongDto updatedSong) {
}
