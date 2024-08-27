package com.study.testcode.users.service;

import com.study.testcode.users.dto.UsersRequestDto;
import com.study.testcode.users.dto.UsersResponseDto;
import com.study.testcode.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    @Transactional(readOnly = true)
    public List<UsersResponseDto.UsersResponse> getUsersList(UsersRequestDto.GetUsersRequest request) {
        return UsersResponseDto.UsersResponse.of(usersRepository.findAll());
    }
}
