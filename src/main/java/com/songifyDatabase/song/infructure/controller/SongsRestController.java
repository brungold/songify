package com.songifyDatabase.song.infructure.controller;

import com.songifyDatabase.song.domain.service.SongAdder;
import com.songifyDatabase.song.domain.service.SongRetriever;
import com.songifyDatabase.song.infructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songifyDatabase.song.infructure.controller.dto.request.CreateSongRequestDto;
import com.songifyDatabase.song.infructure.controller.dto.request.UpdateSongRequestDto;
import com.songifyDatabase.song.domain.model.SongNotFoundException;
import com.songifyDatabase.song.domain.model.Song;
import com.songifyDatabase.song.infructure.controller.dto.response.*;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@Log4j2
@RequestMapping("/songs")
public class SongsRestController {

    private final SongAdder songAdder;
    private final SongRetriever songRetriever;

    public SongsRestController(SongAdder songAdder, SongRetriever songRetriever) {
        this.songAdder = songAdder;
        this.songRetriever = songRetriever;
    }


    //GET /songs + GET query Param /songs?id=100
    @GetMapping
    public ResponseEntity<GetAllSongsResponseDto> getAllSongs(@RequestParam(required = false) Integer limit) {
        List<Song> allSongs = songRetriever.findAll();
        if (limit != null) {
            List<Song> limitedMap = songRetriever.findAllLimitedBy(limit);
            GetAllSongsResponseDto response = new GetAllSongsResponseDto(limitedMap);
            return ResponseEntity.ok(response);
        }
        GetAllSongsResponseDto response = SongMapper.mapFromSongToGetAllSongsResponseDto(allSongs);
        return ResponseEntity.ok(response);

    }


    //GET /songs?id=100
    @GetMapping("/{id}")
    public ResponseEntity<GetSongResponseDto> getSongById(@PathVariable Integer id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        List<Song> allSongs = songRetriever.findAll();
        if (!allSongs.contains(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        Song song = allSongs.get(id);
        GetSongResponseDto response = SongMapper.mapFromSongToGetSongResponseDto(song);
        return ResponseEntity.ok(response);
    }



    @PostMapping
    public ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid CreateSongRequestDto request) {
        Song song = SongMapper.mapFromCreateSongRequestDtoToSong(request);
        songAdder.addSong(song);
        CreateSongResponseDto body = SongMapper.mapFromSongToCreateSongResponseDto(song);
        return ResponseEntity.ok(body);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Integer id) {
        List<Song> allSongs = songRetriever.findAll();
        if (!allSongs.contains(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        allSongs.remove(id);
        DeleteSongResponseDto body = SongMapper.mapFromSongToDeleteSongResponseDto(id);
        return ResponseEntity.ok(body);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UpdateSongResponseDto> update(@PathVariable Integer id,
                                                        @RequestBody @Valid UpdateSongRequestDto request) {
        List<Song> allSongs = songRetriever.findAll();
        if (!allSongs.contains(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }

        Song newSong = SongMapper.mapFromUpdateSongRequestDtoToSong(request);
        Song oldSong = songAdder.addSong(newSong);
        log.info("Updated songName with id: " + id +
                " with oldSongName: " + oldSong.getName() + " to newSongName: " + newSong.getName() +
                " oldArtist: " + oldSong.getArtist() + " to newArtist: " + newSong.getArtist());
        UpdateSongResponseDto body = SongMapper.mapFromSongToUpdateSongResponseDto(newSong);
        return ResponseEntity.ok((body));
    }




    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> partiallyUpdateSong(@PathVariable Integer id,
                                                                              @RequestBody PartiallyUpdateSongRequestDto request) {
        List<Song> allSongs = songRetriever.findAll();
        if (!allSongs.contains(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        Song songFromDatabase = allSongs.get(id);
        Song updatedSong = SongMapper.mapFromPartiallyUpdateSongRequestDtoToSong(request);
        Song.SongBuilder builder = Song.builder(); // gdzie zwieńczene build ??
        if (request.songName() != null) {
            builder.name(updatedSong.getName());
            log.info("partially updated songName");
        } else {
            builder.name(songFromDatabase.getName());
        }
        if (request.artist() != null) {
            builder.artist(updatedSong.getArtist());
            log.info("partially updated artist");
        } else {
            builder.artist(songFromDatabase.getArtist());
        }
        songAdder.addSong(updatedSong);
        PartiallyUpdateSongResponseDto body = SongMapper.mapFromSongToPartiallyUpdateSongResponseDto(updatedSong);
        return ResponseEntity.ok(body);
    }
}
