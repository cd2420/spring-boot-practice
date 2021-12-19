package com.example.corona.dto;

import com.example.corona.constant.PlaceType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PlaceDTO {

    private PlaceType placeType;
    private String placeName;
    private String address;
    private String phoneNumber;
    private Integer capacity;
    private String memo;

    public static PlaceDTO of(
            PlaceType placeType,
            String placeName,
            String address,
            String phoneNumber,
            Integer capacity,
            String memo
    ) {
        return PlaceDTO.builder()
                .placeType(placeType)
                .placeName(placeName)
                .address(address)
                .phoneNumber(phoneNumber)
                .capacity(capacity)
                .memo(memo)
                .build();
    }

}
