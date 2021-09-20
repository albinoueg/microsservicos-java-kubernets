package br.com.albinomoreira.productapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDTO {

    private int status;
    private String message;
    private LocalDateTime timestamp;
}
