package com.tokopakde.toryment.exceptiohandler;

import com.tokopakde.toryment.dto.ErrorResDTO;
import com.tokopakde.toryment.exceptiohandler.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleDataNotFoundException(NotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        var errorMessage = e.getMessage();

        return new ResponseEntity<>(new ErrorResDTO<>(errorMessage), httpStatus);
    }

    @ExceptionHandler(DataIntegrationException.class)
    public ResponseEntity<?> handleVersionNotMatchException(DataIntegrationException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        var message = e.getMessage();

        return new ResponseEntity<>(new ErrorResDTO<>(message), httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errors = e.getBindingResult().getAllErrors().stream()
                .map((ObjectError oe) -> oe.getDefaultMessage()).toList();
        return new ResponseEntity<>(new ErrorResDTO<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidUUIDException.class)
    public ResponseEntity<?> handleUUIDNotValidException(InvalidUUIDException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
         var errorMessage = e.getMessage();

         return new ResponseEntity<>(new ErrorResDTO<>(errorMessage),  httpStatus);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<?> handleDuplicateException(DuplicateException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        var errorMessage = e.getMessage();

        return new ResponseEntity<>(new ErrorResDTO<>(errorMessage),  httpStatus);
    }

    @ExceptionHandler(NotAllowedException.class)
    public ResponseEntity<?> handleNotAllowedException(NotAllowedException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        var errorMessage = e.getMessage();

        return new ResponseEntity<>(new ErrorResDTO<>(errorMessage),  httpStatus);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handleForbiddenException(ForbiddenException e) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        var errorMessage = e.getMessage();

        return new ResponseEntity<>(new ErrorResDTO<>(errorMessage),  httpStatus);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException e) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        var errorMessage = e.getMessage();

        return new ResponseEntity<>(new ErrorResDTO<>(errorMessage),  httpStatus);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<?> handleInsufficientStockException(InsufficientStockException e) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        var errorMessage = e.getMessage();

        return new ResponseEntity<>(new ErrorResDTO<>(errorMessage),  httpStatus);
    }
}
