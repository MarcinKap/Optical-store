package com.opticalstore.repositories;


import com.opticalstore.models.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CountriesRepository  extends JpaRepository<Countries, Long> {
}
