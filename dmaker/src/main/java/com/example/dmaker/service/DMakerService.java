package com.example.dmaker.service;

import com.example.dmaker.dto.CreateDeveloper;
import com.example.dmaker.dto.DeveloperDetailDto;
import com.example.dmaker.dto.DeveloperDto;
import com.example.dmaker.dto.DeveloperEditDto;
import com.example.dmaker.entity.Developer;
import com.example.dmaker.entity.RetiredDeveloper;
import com.example.dmaker.exception.DMakerException;
import com.example.dmaker.repository.DeveloperRepository;
import com.example.dmaker.repository.RetiredDeveloperRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.dmaker.exception.DMakerErrorCode.DUPLICATED_MEMBER_ID;
import static com.example.dmaker.exception.DMakerErrorCode.WRONG_MEMBER_ID;
import static com.example.dmaker.type.StatusCode.EMPLOYED;
import static com.example.dmaker.type.StatusCode.RETIRED;

@Slf4j
@Service
@RequiredArgsConstructor
public class DMakerService {

    private final DeveloperRepository developerRepository;
    private final RetiredDeveloperRepository retiredDeveloperRepository;

    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {

        validateCreateDeveloperRequest(request);
        return CreateDeveloper.Response.fromEntity(developerRepository.save(createDeveloperFromRequest(request)));
    }

    private Developer createDeveloperFromRequest(CreateDeveloper.Request request) {
        return Developer.builder()
                .age(request.getAge())
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .memberId(request.getMemberId())
                .experienceYear(request.getExperienceYear())
                .name(request.getName())
                .statusCode(EMPLOYED)
                .build();
    }

    @Transactional(readOnly = true)
    private void validateCreateDeveloperRequest(@NonNull CreateDeveloper.Request request) {
        request.getDeveloperLevel().validateExperienceYears(request.getExperienceYear());

        developerRepository.findByMemberId(request.getMemberId())
                .ifPresent((developer -> {
                    throw new DMakerException(DUPLICATED_MEMBER_ID);
                }));
    }

    @Transactional(readOnly = true)
    public List<DeveloperDto> getAllEmployedDeveloper() {
        return developerRepository.findDevelopersByStatusCodeEquals(EMPLOYED)
                .stream().map(DeveloperDto::fromEntity)
                        .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DeveloperDetailDto getDeveloper(String memberId) {
        return DeveloperDetailDto.fromEntity(getDeveloperByMemberId(memberId));
    }

    private Developer getDeveloperByMemberId(String memberId) {
        return developerRepository.findByMemberId(memberId).orElseThrow(
                () -> new DMakerException(WRONG_MEMBER_ID)
        );
    }

    @Transactional
    public DeveloperDetailDto editDeveloper(String memberId, DeveloperEditDto.request request) {

        request.getDeveloperLevel().validateExperienceYears(request.getExperienceYear());

        return DeveloperDetailDto.fromEntity(
                getUpdateDeveloperFromRequest(request, getDeveloperByMemberId(memberId))
        );
    }

    private Developer getUpdateDeveloperFromRequest(DeveloperEditDto.request request, Developer developer) {
        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYear(request.getExperienceYear());
        return developer;
    }

    @Transactional
    public DeveloperDetailDto deleteDeveloper(String memberId) {

        return DeveloperDetailDto.fromEntity(
                getDeleteDeveloper(getDeveloperByMemberId(memberId))
        );
    }

    private Developer getDeleteDeveloper(Developer developer) {
        developer.setStatusCode(RETIRED);

        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                                                .name(developer.getName())
                                                .memberId(developer.getMemberId())
                                                .build();
        retiredDeveloperRepository.save(retiredDeveloper);

        return  developer;
    }
}
