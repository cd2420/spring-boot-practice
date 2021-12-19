package com.example.corona.controller.api;

import com.example.corona.constant.PlaceType;
import com.example.corona.dto.APIDataResponse;
import com.example.corona.dto.PlaceDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/place")
@RestController
public class APIPlaceController {

    @GetMapping("")
    public APIDataResponse<List<PlaceDTO>> getPlaces() {
        return APIDataResponse.of(List.of(PlaceDTO.of(
                PlaceType.COMMON
                , "놀자판"
                , "서울시 강남"
                , "010-1234-5678"
                , 10
                , "컴"
        )));
    }

    @PostMapping("")
    public Boolean createPlace() {
        return true;
    }

    @GetMapping("/{placeId}")
    public APIDataResponse<PlaceDTO> getPlace(
            @PathVariable final Integer placeId
    ) {

        if (placeId.equals(2)) {
            return APIDataResponse.of(null);
        }

        return APIDataResponse.of(PlaceDTO.of(
                PlaceType.COMMON
                , "놀자판"
                , "서울시 강남"
                , "010-1234-5678"
                , 10
                , "컴"
        ));
    }

    @PutMapping("/{placeId}")
    public Boolean updatePlace(
            @PathVariable final Integer placeId
    ) {
        return true;
    }

    @DeleteMapping("/{placeId}")
    public Boolean deletePlace(
            @PathVariable final Integer placeId
    ) {
        return true;
    }

}
