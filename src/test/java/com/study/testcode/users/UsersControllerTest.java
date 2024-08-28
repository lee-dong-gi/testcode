package com.study.testcode.users;

import com.study.testcode.config.UsersTestConfig;
import com.study.testcode.users.dto.UsersRequestDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.nio.charset.StandardCharsets;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(locations = CONFIG_TEST)
@Import(UsersTestConfig.class)
public class UsersControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(UsersControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUsers() throws Exception {
        // given
        UsersRequestDto.GetUsersRequest request = new UsersRequestDto.GetUsersRequest();

        // Convert request object to MultiValueMap
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("searchOption", request.getSearchOption());
        params.add("searchKeyword", request.getSearchKeyword());

        // when & then
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(params)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(1));

        // 응답 내용 로깅 및 검증
        MvcResult mvcResult = result.andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        logger.info("Response Body :: {}", responseBody);

        // JSON 응답을 파싱하여 검증
        assertThat(responseBody).contains("이동기", "oksk4753@gmail.com");
    }
}
