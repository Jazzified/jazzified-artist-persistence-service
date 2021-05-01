package dev.tobiadegbuji.artistpersistenceservice.exceptions;

import lombok.Builder;

@Builder
public class ErrorResponse {

    private String errorMessage;

    private String faultCode;
}
