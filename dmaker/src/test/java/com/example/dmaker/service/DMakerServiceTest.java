package com.example.dmaker.service;

import com.example.dmaker.dto.CreateDeveloper;
import com.example.dmaker.dto.DeveloperDetailDto;
import com.example.dmaker.entity.Developer;
import com.example.dmaker.repository.DeveloperRepository;
import com.example.dmaker.type.DeveloperLevel;
import com.example.dmaker.type.DeveloperSkillType;
import com.example.dmaker.type.StatusCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DMakerServiceTest {

    @Mock
    private DeveloperRepository developerRepository;

    @InjectMocks
    private DMakerService dMakerService;

    private final Developer defaultDeveloper = Developer.builder()
            .developerLevel(DeveloperLevel.SENIOR)
            .developerSkillType(DeveloperSkillType.FRONT_END)
            .statusCode(StatusCode.EMPLOYED)
            .name("아임그루트")
            .experienceYear(13)
            .memberId("아임루트그루트")
            .age(10)
            .build();

    private CreateDeveloper.Request getCreateRequest(
            DeveloperLevel developerLevel,
            DeveloperSkillType developerSkillType,
            Integer experienceYears
    ) {
        return CreateDeveloper.Request.builder()
                .developerLevel(developerLevel)
                .developerSkillType(developerSkillType)
                .experienceYear(experienceYears)
                .memberId("teststest1")
                .name("nametest")
                .age(33)
                .build();
    }

    @Test
    @DisplayName("db에서 값 가져오는거 테스트 확인")
    public void testSomething() {
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(defaultDeveloper));

        DeveloperDetailDto developerDetailDto = dMakerService.getDeveloper("asdw");

        assertEquals(DeveloperLevel.SENIOR, developerDetailDto.getDeveloperLevel());
        assertEquals(DeveloperSkillType.FRONT_END, developerDetailDto.getDeveloperSkillType());
        assertEquals(Optional.of(5), Optional.ofNullable(developerDetailDto.getExperienceYear()));
    }

    @Test
    @DisplayName("createDeveloper 성공 테스트")
    public void createDeveloper_success() {
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.empty());
        given(developerRepository.save(any()))
                .willReturn(defaultDeveloper);
        ArgumentCaptor<Developer> captor = ArgumentCaptor.forClass(Developer.class);

        dMakerService.createDeveloper(getCreateRequest(DeveloperLevel.JUNIOR, DeveloperSkillType.BACK_END, 3));

        verify(developerRepository, times(1))
                .save(captor.capture());
        Developer savedDeveloper = captor.getValue();
        assertEquals(DeveloperLevel.JUNIOR, savedDeveloper.getDeveloperLevel());
        assertEquals(DeveloperSkillType.BACK_END, savedDeveloper.getDeveloperSkillType());
        assertEquals(Optional.of(3), Optional.ofNullable(savedDeveloper.getExperienceYear()));
    }

}