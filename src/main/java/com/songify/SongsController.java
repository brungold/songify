package com.songify;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class SongsController {
    Map<Integer, String> database = new HashMap<>();
    //GET /songs + GET query Param /songs?id=100
    @GetMapping("/songs")
    public ResponseEntity<SongResponseDto> getAllSongs(@RequestParam(required = false) Integer limit){
        database.put(1, "shawnmendes song1");
        database.put(2, "metallica one");
        database.put(3, "sting two");
        database.put(4, "barnaba three");
        if(limit != null){
            Map<Integer, String> limitedMap = database.entrySet()
                    .stream()
                    .limit(limit)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            String song = database.get(limit);
            SongResponseDto response = new SongResponseDto(limitedMap);
            return ResponseEntity.ok(response);
        }
        SongResponseDto response = new SongResponseDto(database);
        return ResponseEntity.ok(response);

    }
    //GET /songs?id=100
    @GetMapping("/songs/{id}")
    public ResponseEntity<SingleSongResponseDto> getSongById(@PathVariable Integer id){
        String song = database.get(id);
        if(song == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        SingleSongResponseDto response = new SingleSongResponseDto(song);
        return ResponseEntity.ok(response);
    }
}
