package com.opticalstore.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Integer> {
    @Query(value = "select u from UserApp u where u.email = ?1")
    Optional<UserApp> findUserAppByName(String email);

    @Query(value = "select u from UserApp u where u.email = ?1")
    UserApp findUserAppByEmail(String email);

    @Query(value = "select u from UserApp u where u.id = ?1")
    UserApp findUserAppById(Integer id);




}
