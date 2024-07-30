package com.songifyDatabase.infrastructure.crud.controller.dto.request;

public record PartiallyUpdateSongRequestDto(

        String songName,

        String artist
) {
}
