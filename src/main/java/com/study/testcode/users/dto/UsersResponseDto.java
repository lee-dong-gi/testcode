package com.study.testcode.users.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.study.testcode.users.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class UsersResponseDto {


    @Getter
    @NoArgsConstructor
    public static class UsersResponse {
        private Long id;
        private String name;
        private String email;

        @Builder
        @QueryProjection
        public UsersResponse(Long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public static List<UsersResponse> of(List<Users> userList) {
            List<UsersResponse> responses = new ArrayList<>();
            userList.forEach(user -> responses.add(of(user)));
            return responses;
        }

        public static UsersResponse of(Users user) {
            return UsersResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
        }
    }
}
