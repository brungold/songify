package com.songify.song.error;

import com.songify.song.controller.SongsRestController;
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
        log.warn("SongNotFoundException while accessing song");
        return new ErrorSongResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
