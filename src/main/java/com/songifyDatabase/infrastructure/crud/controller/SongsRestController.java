package com.songifyDatabase.infrastructure.crud.controller;

import com.songifyDatabase.domain.crud.song.SongCrudFacade;
import com.songifyDatabase.domain.crud.song.dto.SongDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.request.CreateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.request.UpdateSongRequestDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.response.CreateSongResponseDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.response.DeleteSongResponseDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.response.GetAllSongsResponseDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.response.GetSongResponseDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.response.PartiallyUpdateSongResponseDto;
import com.songifyDatabase.infrastructure.crud.controller.dto.response.UpdateSongResponseDto;
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

import static com.songifyDatabase.infrastructure.crud.controller.SongControllerMapper.mapFromCreateSongRequestDtoToSongDto;
import static com.songifyDatabase.infrastructure.crud.controller.SongControllerMapper.mapFromPartiallyUpdateSongRequestDtoToSong;
import static com.songifyDatabase.infrastructure.crud.controller.SongControllerMapper.mapFromSongDtoToPartiallyUpdateSongResponseDto;
import static com.songifyDatabase.infrastructure.crud.controller.SongControllerMapper.mapFromSongToCreateSongResponseDto;
import static com.songifyDatabase.infrastructure.crud.controller.SongControllerMapper.mapFromSongToDeleteSongResponseDto;
import static com.songifyDatabase.infrastructure.crud.controller.SongControllerMapper.mapFromSongToGetAllSongsResponseDto;
import static com.songifyDatabase.infrastructure.crud.controller.SongControllerMapper.mapFromSongToGetSongResponseDto;
import static com.songifyDatabase.infrastructure.crud.controller.SongControllerMapper.mapFromSongToUpdateSongResponseDto;
import static com.songifyDatabase.infrastructure.crud.controller.SongControllerMapper.mapFromUpdateSongRequestDtoToSongDto;


@RestController
@Log4j2
@RequestMapping("/songs")
@AllArgsConstructor
public class SongsRestController {


    private final SongCrudFacade songFacade;


    //GET /songs + GET query Param /songs?id=100
    @GetMapping
    ResponseEntity<GetAllSongsResponseDto> getAllSongs(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<SongDto> allSongs = songFacade.findAll(pageable);
        GetAllSongsResponseDto response = mapFromSongToGetAllSongsResponseDto(allSongs);
        return ResponseEntity.ok(response);
    }

    //GET /songs?id=100
    @GetMapping("/{id}")
    ResponseEntity<GetSongResponseDto> getSongById(@PathVariable Long id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        SongDto song = songFacade.findSongDtoById(id);
        GetSongResponseDto response = mapFromSongToGetSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid CreateSongRequestDto request) {
        SongDto songDto = mapFromCreateSongRequestDtoToSongDto(request);
        SongDto savedSong = songFacade.addSong(songDto);
        CreateSongResponseDto body = mapFromSongToCreateSongResponseDto(savedSong);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Long id) {
        songFacade.deleteById(id);
        DeleteSongResponseDto body = mapFromSongToDeleteSongResponseDto(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    ResponseEntity<UpdateSongResponseDto> update(@PathVariable Long id,
                                                 @RequestBody @Valid UpdateSongRequestDto request) {
        SongDto newSongDto = mapFromUpdateSongRequestDtoToSongDto(request);
        songFacade.updateById(id, newSongDto);
        UpdateSongResponseDto body = mapFromSongToUpdateSongResponseDto(newSongDto);
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{id}")
    ResponseEntity<PartiallyUpdateSongResponseDto> partiallyUpdateSong(@PathVariable Long id,
                                                                       @RequestBody PartiallyUpdateSongRequestDto request) {
        SongDto updatedSong = mapFromPartiallyUpdateSongRequestDtoToSong(request);
        SongDto savedSong = songFacade.updatePartiallyById(id, updatedSong);
        PartiallyUpdateSongResponseDto body = mapFromSongDtoToPartiallyUpdateSongResponseDto(savedSong);
        return ResponseEntity.ok(body);
    }
}
