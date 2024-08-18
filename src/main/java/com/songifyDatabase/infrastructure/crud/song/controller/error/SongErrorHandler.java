package com.songifyDatabase.infrastructure.crud.song.controller.error;

import com.songifyDatabase.infrastructure.crud.song.SongsRestController;
import com.songifyDatabase.domain.crud.SongNotFoundException;
import com.songifyDatabase.infrastructure.crud.song.controller.error.dto.ErrorSongResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(assignableTypes = SongsRestController.class)
@Log4j2
public class SongErrorHandler {
    @ExceptionHandler(SongNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorSongResponseDto handleException(SongNotFoundException exception){
        log.warn("SongNotFoundException while accessing songName");
        return new ErrorSongResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
