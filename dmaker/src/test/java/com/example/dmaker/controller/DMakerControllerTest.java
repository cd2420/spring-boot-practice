package com.example.dmaker.controller;

import com.example.dmaker.dto.DeveloperDto;
import com.example.dmaker.service.DMakerService;
import com.example.dmaker.type.DeveloperLevel;
import com.example.dmaker.type.DeveloperSkillType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DMakerController.class)
class DMakerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DMakerService dMakerService;

    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType()
                                                    , MediaType.APPLICATION_JSON.getSubtype()
                                                    , StandardCharsets.UTF_8);

    @Test
    void getDevelopers() throws Exception {
        DeveloperDto juniorDeveloperDto = DeveloperDto.builder()
                .developerSkillType(DeveloperSkillType.BACK_END)
                .developerLevel(DeveloperLevel.JUNIOR)
                .memberId("TEST_DEVELOPER_JUNIOR")
                .build();

        DeveloperDto seniorDeveloperDto = DeveloperDto.builder()
                .developerSkillType(DeveloperSkillType.FULL_STACK)
                .developerLevel(DeveloperLevel.SENIOR)
                .memberId("TEST_DEVELOPER_SENIOR")
                .build();

        given(dMakerService.getAllEmployedDeveloper())
                .willReturn(Arrays.asList(juniorDeveloperDto, seniorDeveloperDto));

        mockMvc.perform(get("/developer").contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(
                        jsonPath("$.[0].developerSkillType"
                                , is(DeveloperSkillType.BACK_END.name())))
                .andExpect(
                        jsonPath("$.[0].developerLevel"
                                , is(DeveloperLevel.JUNIOR.name())))
                .andExpect(
                        jsonPath("$.[1].developerSkillType"
                                , is(DeveloperSkillType.FULL_STACK.name())))
                .andExpect(
                        jsonPath("$.[1].developerLevel", is(DeveloperLevel.SENIOR.name()))
                );

    }


}