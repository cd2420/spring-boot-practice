package com.example.corona.controller.api;

import com.example.corona.constant.ErrorCode;
import com.example.corona.constant.PlaceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(APIPlaceController.class)
class APIPlaceControllerTest {


    private final MockMvc mvc;

    public APIPlaceControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[API] [GET] 장소 전체 조회")
    @Test
    void givenNothing_whenRequestingPlaces_thenReturnsListOfPlaces() throws Exception {
        mvc.perform(get("/api/place"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].placeType").value(PlaceType.COMMON.name()))
                .andExpect(jsonPath("$.data[0].placeName").value("놀자판"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()))
                ;
    }

    @DisplayName("[API] [GET] 장소 단일 조회 - 장소 있는 경우")
    @Test
    void givenPlaceId_whenRequestingPlace_thenReturnsPlace() throws Exception {

        // given
        int placeId = 1;

        mvc.perform(get("/api/place/" + placeId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data.placeType").value(PlaceType.COMMON.name()))
                .andExpect(jsonPath("$.data.placeName").value("놀자판"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()))
        ;
    }

    @DisplayName("[API] [GET] 장소 단일 조회 - 장소 없는 경우")
    @Test
    void givenPlaceId_whenRequestingPlace_thenReturnsEmptyPlace() throws Exception {

        // given
        int placeId = 2;

        mvc.perform(get("/api/place/" + placeId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()))
        ;
    }

}