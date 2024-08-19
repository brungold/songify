package com.songifyDatabase.infrastructure.crud.album;

import com.songifyDatabase.domain.crud.SongifyCrudFacade;
import com.songifyDatabase.domain.crud.dto.AlbumDto;
import com.songifyDatabase.domain.crud.dto.AlbumRequestDto;
import com.songifyDatabase.domain.crud.dto.ArtistDto;
import com.songifyDatabase.domain.crud.dto.ArtistRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/album")
class AlbumController {
    private final SongifyCrudFacade songifyCrudFacade;

    @PostMapping()
    ResponseEntity<AlbumDto> postAlbum(@RequestBody AlbumRequestDto albumRequestDto) {
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(albumRequestDto);
        return ResponseEntity.ok(albumDto);
    }
}