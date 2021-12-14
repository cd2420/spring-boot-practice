package com.example.dmaker.controller;

import com.example.dmaker.dto.CreateDeveloper;
import com.example.dmaker.dto.DeveloperDetailDto;
import com.example.dmaker.dto.DeveloperDto;
import com.example.dmaker.dto.DeveloperEditDto;
import com.example.dmaker.service.DMakerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DMakerController {

    private final DMakerService dMakerService;

    @PostMapping("/developer")
    public CreateDeveloper.Response createDeveloper(@Valid @RequestBody final CreateDeveloper.Request request){
        log.info("request = {}", request);
        return dMakerService.createDeveloper(request);
    }

    @GetMapping("/developer")
    public List<DeveloperDto> getAllDeveloper() {
        return dMakerService.getAllEmployedDeveloper();
    }

    @GetMapping("/developer/{memberId}")
    public DeveloperDetailDto getDeveloperDetail(
            @PathVariable final String memberId
    ) {
        return dMakerService.getDeveloper(memberId);
    }

    @PutMapping("/developer/{memberId}")
    public DeveloperDetailDto editDeveloper(
            @PathVariable final String memberId,
            @Valid @RequestBody DeveloperEditDto.request request
    ) {
        return dMakerService.editDeveloper(memberId, request);
    }

    @DeleteMapping("/developer/{memberId}")
    public DeveloperDetailDto deleteDeveloper(
            @PathVariable final String memberId
    ) {
        return dMakerService.deleteDeveloper(memberId);
    }


}
