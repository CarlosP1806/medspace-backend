package com.medspace.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private boolean success;
    private String message;
    private Object data;

    public static ResponseDTO success(String message, Object data) {
        return new ResponseDTO(true, message, data);
    }

    public static ResponseDTO success(String message) {
        return new ResponseDTO(true, message, null);
    }

    public static ResponseDTO error(String errorMessage) {
        return new ResponseDTO(false, errorMessage, null);
    }

}
