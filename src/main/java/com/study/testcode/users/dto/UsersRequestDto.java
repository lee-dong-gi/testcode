package com.study.testcode.users.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsersRequestDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class GetUsersRequest {
        private String searchKeyword;
        private SearchOptionEnum searchOption;
    }

    public enum SearchOptionEnum {
        ID, NAME, EMAIL
    }
}
