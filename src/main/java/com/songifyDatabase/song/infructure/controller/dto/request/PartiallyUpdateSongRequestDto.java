package com.songifyDatabase.song.infructure.controller.dto.request;

public record PartiallyUpdateSongRequestDto(

        String songName,

        String artist
) {
}
