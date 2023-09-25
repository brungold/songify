package com.songifyDatabase.song.infructure.controller.dto.response;

import com.songifyDatabase.song.domain.model.Song;

import java.util.List;
import java.util.Map;

public record GetAllSongsResponseDto(List<Song> songs) {
}
