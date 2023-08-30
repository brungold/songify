package com.songify.song.infructure.controller;

import com.songify.song.infructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songify.song.infructure.controller.dto.request.CreateSongRequestDto;
import com.songify.song.infructure.controller.dto.request.UpdateSongRequestDto;
import com.songify.song.domain.model.SongNotFoundException;
import com.songify.song.domain.model.Song;
import com.songify.song.infructure.controller.dto.response.*;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequestMapping("/songs")
public class SongsRestController {
    Map<Integer, Song> database = new HashMap<>(Map.of(
            1, new Song("song1", "Shawn Mendes"),
            2, new Song("One", "Metallica"),
            3, new Song("Fire", "Jimi Hendrix"),
            4, new Song("Go!", "Barnaba")
    ));

    //GET /songs + GET query Param /songs?id=100
    @GetMapping
    public ResponseEntity<GetAllSongsResponseDto> getAllSongs(@RequestParam(required = false) Integer limit) {
        if (limit != null) {
            Map<Integer, Song> limitedMap = database.entrySet()
                    .stream()
                    .limit(limit)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            GetAllSongsResponseDto response = new GetAllSongsResponseDto(limitedMap);
            return ResponseEntity.ok(response);
        }
        GetAllSongsResponseDto response = SongMapper.mapFromSongToGetAllSongsResponseDto(database);
        return ResponseEntity.ok(response);

    }


    //GET /songs?id=100
    @GetMapping("/{id}")
    public ResponseEntity<GetSongResponseDto> getSongById(@PathVariable Integer id,
                                                          @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        if (!database.containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        Song song = database.get(id);
        GetSongResponseDto response = SongMapper.mapFromSongToGetSongResponseDto(song);
        return ResponseEntity.ok(response);
    }



    @PostMapping
    public ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid CreateSongRequestDto request) {
        Song song = SongMapper.mapFromCreateSongRequestDtoToSong(request);
        log.info("adding new song: " + song);
        database.put(database.size() + 1, song);
        CreateSongResponseDto body = SongMapper.mapFromSongToCreateSongResponseDto(song);
        return ResponseEntity.ok(body);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Integer id) {
        if (!database.containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        database.remove(id);
        DeleteSongResponseDto body = SongMapper.mapFromSongToDeleteSongResponseDto(id);
        return ResponseEntity.ok(body);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UpdateSongResponseDto> update(@PathVariable Integer id,
                                                        @RequestBody @Valid UpdateSongRequestDto request) {
        if (!database.containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }

        Song newSong = SongMapper.mapFromUpdateSongRequestDtoToSong(request);
        Song oldSong = database.put(id, newSong);
        log.info("Updated song with id: " + id +
                " with oldSongName: " + oldSong.name() + " to newSongName: " + newSong.name() +
                " oldArtist: " + oldSong.artist() + " to newArtist: " + newSong.artist());
        UpdateSongResponseDto body = SongMapper.mapFromSongToUpdateSongResponseDto(newSong);
        return ResponseEntity.ok((body));
    }




    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> partiallyUpdateSong(@PathVariable Integer id,
                                                                              @RequestBody PartiallyUpdateSongRequestDto request) {
        if (!database.containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        Song songFromDatabase = database.get(id);
        Song updatedSong = SongMapper.mapFromPartiallyUpdateSongRequestDtoToSong(request);
        Song.SongBuilder builder = Song.builder(); // gdzie zwieńczene build ??
        if (request.songName() != null) {
            builder.name(updatedSong.name());
            log.info("partially updated songName");
        } else {
            builder.name(songFromDatabase.name());
        }
        if (request.artist() != null) {
            builder.artist(updatedSong.artist());
            log.info("partially updated artist");
        } else {
            builder.artist(songFromDatabase.artist());
        }
        database.put(id, updatedSong);
        PartiallyUpdateSongResponseDto body = SongMapper.mapFromSongToPartiallyUpdateSongResponseDto(updatedSong);
        return ResponseEntity.ok(body);
    }
}
