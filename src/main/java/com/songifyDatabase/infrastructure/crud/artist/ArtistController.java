package com.songifyDatabase.infrastructure.crud.artist;

import com.songifyDatabase.domain.crud.SongifyCrudFacade;
import com.songifyDatabase.domain.crud.dto.ArtistDto;
import com.songifyDatabase.domain.crud.dto.ArtistRequestDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/artists")
class ArtistController {
    private final SongifyCrudFacade songifyCrudFacade;

    @GetMapping()
    ResponseEntity<AllArtistsDto> getArtist(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Set<ArtistDto> artists = songifyCrudFacade.findAllArtists(pageable);
        AllArtistsDto allArtistsDto = new AllArtistsDto(artists);
        return ResponseEntity.ok(allArtistsDto);
    }

    @PostMapping()
    ResponseEntity<ArtistDto> postArtist(@RequestBody ArtistRequestDto artistRequestDto) {
        ArtistDto artistDto = songifyCrudFacade.addArtist(artistRequestDto);
        return ResponseEntity.ok(artistDto);
    }


    @DeleteMapping("/{artistId}")
    ResponseEntity<String> deleteArtistWithAllAlbumsAndSongs(@PathVariable Long artistId) {
        songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(artistId);
        return ResponseEntity.ok("probably all deleted");
    }

    @PutMapping("/{artistId}/{albumId}")
    ResponseEntity<String> addArtistToAlbum(@PathVariable Long artistId, @PathVariable Long albumId) {
        songifyCrudFacade.addArtistToAlbum(artistId, albumId);
        return ResponseEntity.ok("probably assigned artist to album.");
    }

    @PatchMapping("/{artistId}")
    ResponseEntity<ArtistDto> updateArtistNameById(@PathVariable Long artistId,
                                                   @Valid @RequestBody ArtistUpdateRequestDto artistUpdateRequestDto) {
        ArtistDto artistDto = songifyCrudFacade.updateArtistNameById(artistId, artistUpdateRequestDto.newArtistName());
        return ResponseEntity.ok(artistDto);
    }
}
