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
    public ErrorDeleteSongResponseDto handleException(SongNotFoundException exception){
        log.warn("error while deleting song ");
        return new ErrorDeleteSongResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
