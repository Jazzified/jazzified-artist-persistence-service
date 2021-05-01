package dev.tobiadegbuji.artistpersistenceservice.advice;

import dev.tobiadegbuji.artistpersistenceservice.exceptions.IdNotFoundException;
import dev.tobiadegbuji.artistpersistenceservice.exceptions.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class ArtistPersistenceAdvice {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleArtistIdNotFoundException(IdNotFoundException e){

        log.error(e::getMessage);

        ErrorResponse errorResponse = getErrorResponse("Order Id: " + e.getId() + ", was not found", "J123");

        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }



    private ErrorResponse getErrorResponse(String errorMessage, String faultCode){

        return ErrorResponse.builder()
                .errorMessage(errorMessage)
                .faultCode(faultCode)
                .build();
    }

}
