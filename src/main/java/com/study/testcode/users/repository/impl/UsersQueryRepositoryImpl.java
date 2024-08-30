package com.study.testcode.users.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.testcode.users.dto.QUsersResponseDto_UsersResponse;
import com.study.testcode.users.dto.UsersRequestDto;
import com.study.testcode.users.dto.UsersResponseDto;
import com.study.testcode.users.repository.UsersQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

import static com.study.testcode.users.entity.QUsers.users;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UsersQueryRepositoryImpl implements UsersQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<UsersResponseDto.UsersResponse> getUsersList(UsersRequestDto.GetUsersRequest request) {
        return queryFactory.select(
                        new QUsersResponseDto_UsersResponse(
                                users.id
                                , users.name
                                , users.email
                        ))
                .from(users)
                .where(usersSearchWhere(request))
                .orderBy(users.id.desc())
                .fetch();
    }

    private BooleanExpression usersSearchWhere(UsersRequestDto.GetUsersRequest request) {
        String keyword = request.getSearchKeyword();
        UsersRequestDto.SearchOptionEnum opt = request.getSearchOption();

        if (!StringUtils.hasText(keyword)) {
            return null;
        }

        if (Objects.isNull(opt)) { // null 이면 전체 검색 취급
            long id;
            try {
                id = Long.parseLong(keyword);
            } catch (NumberFormatException e) {
                id = 0;
            }

            return users.name.contains(keyword)
                    .or(users.id.eq(id))
                    .or(users.email.contains(keyword));
        }

        switch (opt) {
            case ID:
                try {
                    long id = Long.parseLong(keyword);
                    return users.id.eq(id);
                } catch (NumberFormatException e) {
                    return users.id.eq(0L);
                }
            case NAME:
                return users.name.contains(keyword);
            case EMAIL:
                return users.email.contains(keyword);
            default:
                break;
        }

        return null;
    }
}
