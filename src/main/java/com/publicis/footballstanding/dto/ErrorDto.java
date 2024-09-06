package com.publicis.footballstanding.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ErrorDto {

    @Schema(description = "status")
    private Integer status;

    @Schema(description = "error")
    private String error;

    @Schema(description = "message")
    private String message;
}
