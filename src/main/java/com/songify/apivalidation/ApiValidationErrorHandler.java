package com.songify.apivalidation;

import com.songify.song.SongsRestController;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

// ContollerAdvice wyższy poziom obsługiwania błędu
@ControllerAdvice(assignableTypes = SongsRestController.class)
public class ApiValidationErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiValidationErrorResponseDto handleValidationException(MethodArgumentNotValidException exception){
        List<String> messages = getErrorFromException(exception);
        return new ApiValidationErrorResponseDto(messages);
    }
    private List<String> getErrorFromException(MethodArgumentNotValidException exception){
        return exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }
}
