package com.publicis.footballstanding.dto;

import lombok.Data;

@Data
public class ErrorResponseDto {

    private String error;

    private String message;
}
