package com.study.testcode.users.controller;

import com.study.testcode.common.dto.CommonDataResponse;
import com.study.testcode.common.dto.CommonResponseStatus;
import com.study.testcode.users.dto.UsersRequestDto;
import com.study.testcode.users.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UsersController {
    private final UsersService usersService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CommonDataResponse<Object> getUsers(@Valid UsersRequestDto.GetUsersRequest request) throws BadRequestException {
        if (
                StringUtils.hasText(request.getSearchKeyword())
                        && "이동기바보".equals(request.getSearchKeyword())
        ) {
            throw new BadRequestException("금칙어 " + request.getSearchKeyword() + "를 입력하셨습니다.");
        }

        return new CommonDataResponse<>(CommonResponseStatus.SUCCESS, usersService.getUsersList(request));
    }
}
