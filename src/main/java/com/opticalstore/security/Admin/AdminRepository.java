package com.opticalstore.security.Admin;



import com.opticalstore.security.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<UserApp, Long> {





}
