package com.mastercs.demo.repository;

import com.mastercs.demo.bean.EnumRole;
import com.mastercs.demo.bean.Role;
import com.mastercs.demo.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(EnumRole name);
}
