package com.shop.user.repositiory;

import com.shop.user.model.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, RevisionRepository<User, Long, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndActivatedCodeIsNull(String email);

    Optional<User> findByActivatedCode(String activatedCode);
}
