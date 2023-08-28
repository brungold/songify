package com.songify.apivalidation;

import com.songify.song.SongsRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// ContollerAdvice wyższy poziom obsługiwania błędu
@ControllerAdvice(assignableTypes = SongsRestController.class)
public class ApiValidationErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiValidationErrorResponseDto handleValidationException(MethodArgumentNotValidException exception){
        String message = exception.getMessage();
        return new ApiValidationErrorResponseDto("handled exception");
    }
}
