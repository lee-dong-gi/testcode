package com.study.testcode.users.repository;

import com.study.testcode.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>, UsersQueryRepository {
    Optional<Users> findByEmail(String email);
}