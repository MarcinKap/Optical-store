package com.opticalstore.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select r from Role r where r.role = ?1")
    Role findRoleByName(String role);
}
