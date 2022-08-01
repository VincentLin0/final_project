package com.mastercs.demo.repository;


import com.mastercs.demo.bean.EnumRole;
import com.mastercs.demo.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    User findUserByUsername(String username);

    User findUserById(Long userid);
}
