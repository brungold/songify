package com.songifyDatabase.infrastructure.crud.artist;

import com.songifyDatabase.domain.crud.dto.ArtistDto;

import java.util.Set;

public record AllArtistsDto(Set<ArtistDto> artists) {
}
