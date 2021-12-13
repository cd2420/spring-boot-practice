package com.example.dmaker.service;

import com.example.dmaker.dto.CreateDeveloper;
import com.example.dmaker.dto.DeveloperDto;
import com.example.dmaker.entity.Developer;
import com.example.dmaker.exception.DMakerException;
import com.example.dmaker.repository.DeveloperRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.dmaker.exception.DMakerErrorCode.DUPLICATED_MEMBER_ID;
import static com.example.dmaker.type.StatusCode.EMPLOYED;

@Slf4j
@Service
@RequiredArgsConstructor
public class DMakerService {

    private final DeveloperRepository developerRepository;

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
}
