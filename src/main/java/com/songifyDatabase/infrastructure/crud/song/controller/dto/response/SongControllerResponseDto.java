package com.songifyDatabase.infrastructure.crud.song.controller.dto.response;

import lombok.Builder;
//Do ewentualnego zmieniania aby Cotroller Song miał oddzielne DTO od domain
@Builder
public record SongControllerResponseDto(Long id, String name) {
}
