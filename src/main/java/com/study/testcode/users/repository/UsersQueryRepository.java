package com.study.testcode.users.repository;


import com.study.testcode.users.dto.UsersRequestDto;
import com.study.testcode.users.dto.UsersResponseDto;

import java.util.List;

public interface UsersQueryRepository {
    List<UsersResponseDto.UsersResponse> getUsersList(UsersRequestDto.GetUsersRequest request);
}
