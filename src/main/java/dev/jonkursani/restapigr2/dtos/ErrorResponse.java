package dev.jonkursani.restapigr2.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
    Map<String, String> validationErrors;
}