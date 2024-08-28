package com.study.testcode.users;

import com.study.testcode.users.entity.Users;
import com.study.testcode.users.repository.UsersRepository;
import com.study.testcode.users.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
public class UserServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

//    @Mock
//    private UsersRepository mockUsersRepository;
//    @InjectMocks
//    private UsersService mockUsersService;

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;

/*    @Test
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
        results.forEach(result -> {
            logger.info("result :: {}", result.getName());
        });

        assertNotNull(results);
        assertEquals(mockUsers.size(), results.size());
        verify(mockUsersRepository, times(1)).findAll();
    }*/

    @BeforeEach
    void setUp() {
        Users user = Users.builder()
                .name("이동기")
                .email("oksk4753@gmail.com")
                .build();

        usersRepository.save(user);
    }

    @Test
    void testGetUsers() {
        // given
        //when
        Optional<Users> result = usersRepository.findByEmail("oksk4753@gmail.com");

        // then
        assertThat(result.isPresent()).isTrue();
    }
}
