package com.example.dmaker.dto;

import com.example.dmaker.entity.Developer;
import com.example.dmaker.type.DeveloperLevel;
import com.example.dmaker.type.DeveloperSkillType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeveloperDto {

    private String memberId;
    private DeveloperLevel developerLevel;
    private DeveloperSkillType developerSkillType;

    public static DeveloperDto fromEntity(Developer developer) {
        return DeveloperDto.builder()
                .memberId(developer.getMemberId())
                .developerLevel(developer.getDeveloperLevel())
                .developerSkillType(developer.getDeveloperSkillType())
                .build();
    }
}
