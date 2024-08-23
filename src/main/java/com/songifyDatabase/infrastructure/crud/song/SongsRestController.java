package com.songifyDatabase.infrastructure.crud.song;

import com.songifyDatabase.domain.crud.SongifyCrudFacade;
import com.songifyDatabase.domain.crud.dto.SongDto;
import com.songifyDatabase.domain.crud.dto.SongRequestDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.request.UpdateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.response.CreateSongResponseDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.response.DeleteSongResponseDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.response.GetAllSongsResponseDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.response.GetSongResponseDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.response.PartiallyUpdateSongResponseDto;
import com.songifyDatabase.infrastructure.crud.song.controller.dto.response.UpdateSongResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.songifyDatabase.infrastructure.crud.song.SongControllerMapper.mapFromSongToCreateSongResponseDto;
import static com.songifyDatabase.infrastructure.crud.song.SongControllerMapper.mapFromSongToDeleteSongResponseDto;
import static com.songifyDatabase.infrastructure.crud.song.SongControllerMapper.mapFromSongToGetAllSongsResponseDto;
import static com.songifyDatabase.infrastructure.crud.song.SongControllerMapper.mapFromSongToGetSongResponseDto;
import static com.songifyDatabase.infrastructure.crud.song.SongControllerMapper.mapFromSongToUpdateSongResponseDto;


@RestController
@Log4j2
@RequestMapping("/songs")
@AllArgsConstructor
public class SongsRestController {


    private final SongifyCrudFacade songFacade;


    //GET /songs + GET query Param /songs?id=100
    @GetMapping
    ResponseEntity<GetAllSongsResponseDto> getAllSongs(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<SongDto> allSongs = songFacade.findAllSongs(pageable);
        GetAllSongsResponseDto response = mapFromSongToGetAllSongsResponseDto(allSongs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<GetSongResponseDto> getSongById(@PathVariable Long id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        SongDto song = songFacade.findSongDtoById(id);
        GetSongResponseDto response = mapFromSongToGetSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid SongRequestDto request) {
        SongDto savedSong = songFacade.addSong(request);
        CreateSongResponseDto body = mapFromSongToCreateSongResponseDto(savedSong);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Long id) {
        songFacade.deleteSongById(id);
        DeleteSongResponseDto body = mapFromSongToDeleteSongResponseDto(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    ResponseEntity<UpdateSongResponseDto> update(@PathVariable Long id,
                                                 @RequestBody @Valid UpdateSongRequestDto request) {
        SongDto newSongDto = SongControllerMapper.mapFromUpdateSongRequestDtoToSongDto(request);
        songFacade.updateSongById(id, newSongDto);
        UpdateSongResponseDto body = mapFromSongToUpdateSongResponseDto(newSongDto);
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{id}")
    ResponseEntity<PartiallyUpdateSongResponseDto> partiallyUpdateSong(@PathVariable Long id,
                                                                       @RequestBody PartiallyUpdateSongRequestDto request) {
        SongDto updatedSong = SongControllerMapper.mapFromPartiallyUpdateSongRequestDtoToSong(request);
        SongDto savedSong = songFacade.updateSongPartiallyById(id, updatedSong);
        PartiallyUpdateSongResponseDto body = SongControllerMapper.mapFromSongDtoToPartiallyUpdateSongResponseDto(savedSong);
        return ResponseEntity.ok(body);
    }

    //    @DeleteMapping("/{id}/genre")
//    ResponseEntity<DeleteSongResponseDto> deleteSongWithGenre(@PathVariable Long id) {
//        songFacade.deleteSongAndGenreById(id);
//        DeleteSongResponseDto body = mapFromSongToDeleteSongResponseDto(id);
//        return ResponseEntity.ok(body);
//    }
}
