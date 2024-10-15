package com.songifyDatabase.infrastructure.crud.album;

import com.songifyDatabase.domain.crud.dto.AlbumDto;

import java.util.Set;

public record GetAllAlbumsResponseDto(Set<AlbumDto> albums) {
}
