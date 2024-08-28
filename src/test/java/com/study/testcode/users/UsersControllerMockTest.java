package com.study.testcode.users;

import com.study.testcode.users.controller.UsersController;
import com.study.testcode.users.dto.UsersRequestDto;
import com.study.testcode.users.dto.UsersResponseDto;
import com.study.testcode.users.entity.Users;
import com.study.testcode.users.repository.UsersRepository;
import com.study.testcode.users.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsersController.class)
@TestPropertySource(locations = CONFIG_TEST)
public class UsersControllerMockTest {
    private static final Logger logger = LoggerFactory.getLogger(UsersControllerMockTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private UsersRepository usersRepository;

    @Test
    void testGetUsers() throws Exception {
        // given 조회 요청 객체 생성
        UsersRequestDto.GetUsersRequest request = new UsersRequestDto.GetUsersRequest();
        request.setSearchOption("name");
        request.setSearchKeyword("이동기");

        // 서비스 메서드가 반환할 Mock 데이터를 리스트로 생성
        List<UsersResponseDto.UsersResponse> mockResponse = new ArrayList<>();
        mockResponse.add(new UsersResponseDto.UsersResponse(1L, "이동기", "oksk4753@gmail.com"));

        // Mockito 를 사용하여 usersService.getUsersList 메서드가 호출될 때,
        // 미리 정의한 mockResponse 를 반환하도록 설정
        when(usersService.getUsersList(any(UsersRequestDto.GetUsersRequest.class))).thenReturn(mockResponse);

        // Convert request object to MultiValueMap
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("searchOption", request.getSearchOption());
        params.add("searchKeyword", request.getSearchKeyword());

        // when & then
        // MockMvc 를 사용해 GET 요청을 /api/v1/users 엔드포인트에 보냄
        // 요청의 Content-Type 은 JSON 형식으로 지정
        ResultActions result = mockMvc.perform(get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(params))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(mockResponse.size()));

        // usersService.getUsersList 메서드가 한 번 호출되었는지 확인
        verify(usersService, times(1)).getUsersList(any(UsersRequestDto.GetUsersRequest.class));

        // 응답 내용을 문자열로 받아와 UTF-8 인코딩을 적용한 후 로깅
        String responseBody = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        logger.info("Response Body :: {}", responseBody);
    }
}
