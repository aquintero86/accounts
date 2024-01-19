package com.ms.exercise.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class ResponseDto {
    private String statusCode;
    private String statusMsg;

}
