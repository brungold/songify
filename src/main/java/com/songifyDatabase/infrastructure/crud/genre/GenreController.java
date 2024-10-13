package com.songifyDatabase.infrastructure.crud.genre;

import com.songifyDatabase.domain.crud.SongifyCrudFacade;
import com.songifyDatabase.domain.crud.dto.GenreDto;
import com.songifyDatabase.domain.crud.dto.GenreRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/genres")
class GenreController {
    private final SongifyCrudFacade songifyCrudFacade;

    @GetMapping
    ResponseEntity<GetAllGenresResponseDto> getGenres() {
        Set<GenreDto> genreDto = songifyCrudFacade.retrieveGenres();
        return ResponseEntity.ok(new GetAllGenresResponseDto(genreDto));
    }

    @PostMapping()
    ResponseEntity<GenreDto> postGenre(@RequestBody GenreRequestDto genreRequestDto) {
        GenreDto genreDto = songifyCrudFacade.addGenre(genreRequestDto);
        return ResponseEntity.ok(genreDto);
    }
}
