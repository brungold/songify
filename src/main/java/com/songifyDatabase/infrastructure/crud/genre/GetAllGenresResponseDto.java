package com.songifyDatabase.infrastructure.crud.genre;

import com.songifyDatabase.domain.crud.dto.GenreDto;

import java.util.Set;

public record GetAllGenresResponseDto(Set<GenreDto> genres) {
}
