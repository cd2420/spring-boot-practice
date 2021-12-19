package com.example.corona.error;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BaseErrorController.class)
class BaseErrorControllerTest {

    private final MockMvc mockMvc;

    public BaseErrorControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @DisplayName("[view] [GET] 에러 페이지 - 페이지 없음")
    @Test
    void givenWrongURI_whenRequestingRootPage_thenReturns4XX() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/wrong-url"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}