package com.example.dmaker.type;

import com.example.dmaker.exception.DMakerException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;

import static com.example.dmaker.constant.DMakerConstant.MAX_JUNIOR_YEAR;
import static com.example.dmaker.constant.DMakerConstant.MIN_SENIOR_YEAR;
import static com.example.dmaker.exception.DMakerErrorCode.LEVEL_EXPERIENCE_YEAR_NOT_MATCH;

@Getter
@AllArgsConstructor
public enum DeveloperLevel {
    NEW("신익 개발자", years -> years == 0),
    JUNIOR("주니어 개발자", years -> years <= MAX_JUNIOR_YEAR),
    JUNGNIOR("중급 개발자", years -> years < MIN_SENIOR_YEAR && years > MAX_JUNIOR_YEAR),
    SENIOR("고급 개발자", years -> years > MIN_SENIOR_YEAR);

    private final String description;
    private final Function<Integer, Boolean> validationFunction;

    public void validateExperienceYears(Integer years) {
        if (!validationFunction.apply(years)) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEAR_NOT_MATCH);
        }
    }

}
