package com.opticalstore.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleAppRepository extends JpaRepository<UserApp, Integer> {



    @Query(value = "select u from UserApp u where u.username = ?1")
    Optional<UserApp> findUserAppByName(String username);



}


