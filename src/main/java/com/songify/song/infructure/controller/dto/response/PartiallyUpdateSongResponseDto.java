package com.songify.song.infructure.controller.dto.response;

import com.songify.song.domain.model.Song;

public record PartiallyUpdateSongResponseDto(Song updatedSong) {
}
