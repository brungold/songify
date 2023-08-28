package com.songify.apivalidation;

import com.songify.song.SongsRestController;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// ContollerAdvice wyższy poziom obsługiwania błędu
@ControllerAdvice(assignableTypes = SongsRestController.class)
public class ApiValidationErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiValidationErrorResponseDto handleValidationException(MethodArgumentNotValidException exception){
        String message = exception.getMessage();
        return new ApiValidationErrorResponseDto("handled exception");
    }
}
