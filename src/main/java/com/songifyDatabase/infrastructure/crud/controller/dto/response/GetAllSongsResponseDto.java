package com.songifyDatabase.infrastructure.crud.controller.dto.response;

import java.util.List;

public record GetAllSongsResponseDto(List<SongControllerResponseDto> songs) {
}
