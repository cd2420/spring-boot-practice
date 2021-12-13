package com.example.dmaker.dto;

import com.example.dmaker.exception.DMakerErrorCode;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DMakerErrorResponse {

    private DMakerErrorCode dMakerErrorCode;
    private String message;
}
