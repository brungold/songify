package com.songifyDatabase.song.infructure.controller.dto.response;

import com.songifyDatabase.song.domain.model.Song;

import java.util.Map;

public record GetAllSongsResponseDto(Map<Integer, Song> songs) {
}
