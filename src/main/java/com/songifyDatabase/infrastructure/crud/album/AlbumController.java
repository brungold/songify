package com.songifyDatabase.infrastructure.crud.album;

import com.songifyDatabase.domain.crud.SongifyCrudFacade;
import com.songifyDatabase.domain.crud.dto.AlbumDto;
import com.songifyDatabase.domain.crud.dto.AlbumDtoWithArtistsAndSongs;
import com.songifyDatabase.domain.crud.dto.AlbumInfo;
import com.songifyDatabase.domain.crud.dto.AlbumRequestDto;
import com.songifyDatabase.domain.crud.dto.ArtistDto;
import com.songifyDatabase.domain.crud.dto.ArtistRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/albums")
class AlbumController {
    private final SongifyCrudFacade songifyCrudFacade;

    @GetMapping
    ResponseEntity<GetAllAlbumsResponseDto> getAllAlbums() {
        Set<AlbumDto> allAlbums = songifyCrudFacade.findAllAlbums();
        GetAllAlbumsResponseDto response = new GetAllAlbumsResponseDto(allAlbums);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{albumId}")
    ResponseEntity<AlbumInfo> getAlbumWithArtistsAndSongs(@PathVariable Long albumId) {
        AlbumInfo albumByIdWithArtistsAndSongs = songifyCrudFacade.findAlbumByIdWithArtistsAndSongs(albumId);
        return ResponseEntity.ok(albumByIdWithArtistsAndSongs);
    }

    @PostMapping()
    ResponseEntity<AlbumDto> postAlbum(@RequestBody AlbumRequestDto albumRequestDto) {
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(albumRequestDto);
        return ResponseEntity.ok(albumDto);
    }
}