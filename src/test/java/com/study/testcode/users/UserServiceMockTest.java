package com.study.testcode.users;

import com.study.testcode.users.dto.UsersRequestDto;
import com.study.testcode.users.dto.UsersResponseDto;
import com.study.testcode.users.entity.Users;
import com.study.testcode.users.repository.UsersRepository;
import com.study.testcode.users.service.UsersService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(locations = CONFIG_TEST)
public class UserServiceMockTest {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceMockTest.class);

    @Mock
    private UsersRepository mockUsersRepository;
    @InjectMocks
    private UsersService mockUsersService;

    @Test
    void mockTestGetUsersList() {
        // given
        UsersRequestDto.GetUsersRequest request = new UsersRequestDto.GetUsersRequest();
        List<Users> mockUsers = new ArrayList<>();
        // 필요한 만큼 Users 객체를 추가해서 테스트 데이터를 설정
        mockUsers.add(new Users(1L, "이동기", "oksk4753@gmail.com"));

        when(mockUsersRepository.findAll()).thenReturn(mockUsers);

        // when
        List<UsersResponseDto.UsersResponse> results = mockUsersService.getUsersList(request);

        // then
        results.forEach(result -> logger.info("result :: {}", result.getName()));
        assertNotNull(results);
        assertEquals(mockUsers.size(), results.size());
        verify(mockUsersRepository, times(1)).findAll();
    }
}
