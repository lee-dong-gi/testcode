package com.study.testcode.users;

import com.study.testcode.users.controller.UsersController;
import com.study.testcode.users.dto.UsersRequestDto;
import com.study.testcode.users.dto.UsersResponseDto;
import com.study.testcode.users.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UsersControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(UsersControllerTest.class);

    @Mock
    private UsersService usersService;

    @InjectMocks
    private UsersController usersController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
    }

    /**
     * UsersServiceTest: UsersService 를 테스트하기 위해 UsersRepository 를 Mockito 로 모킹하고, getUsersList() 메서드의 반환값을 검증합니다.
     * UsersControllerTest: MockMvc 를 사용해 UsersController 의 GET /api/v1/users 요청을 테스트합니다.
     * usersService.getUsersList()가 올바르게 호출되는지와 그 결과가 JSON 형식으로 응답되는지를 검증합니다.
     */
    @Test
    void testGetUsers() throws Exception {
        // given
        UsersRequestDto.GetUsersRequest request = new UsersRequestDto.GetUsersRequest();
        List<UsersResponseDto.UsersResponse> mockResponse = new ArrayList<>();

        // 필요한 만큼 UsersResponse 객체를 추가해서 테스트 데이터를 설정
        mockResponse.add(new UsersResponseDto.UsersResponse(1L, "이동기", "oksk4753@gmail.com"));

        when(usersService.getUsersList(any())).thenReturn(mockResponse);

        // when & then
        ResultActions result = mockMvc.perform(get("/api/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // 콘솔에 찍음
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(mockResponse.size()));

        verify(usersService, times(1)).getUsersList(any());

        // 응답 내용 로깅
        String responseBody = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        logger.info("Response Body :: {}", responseBody);
    }
}
