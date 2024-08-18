package com.songifyDatabase.infrastructure.crud.genre;

import com.songifyDatabase.domain.crud.SongifyCrudFacade;
import com.songifyDatabase.domain.crud.dto.GenreDto;
import com.songifyDatabase.domain.crud.dto.GenreRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/genre")
class GenreController {
    private final SongifyCrudFacade songifyCrudFacade;

    @PostMapping()
    ResponseEntity<GenreDto> postGenre(@RequestBody GenreRequestDto genreRequestDto) {
        GenreDto genreDto = songifyCrudFacade.addGenre(genreRequestDto);
        return ResponseEntity.ok(genreDto);
    }
}
