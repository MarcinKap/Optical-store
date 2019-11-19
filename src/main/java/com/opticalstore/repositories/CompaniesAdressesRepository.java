package com.opticalstore.repositories;


import com.opticalstore.models.Adresses;
import com.opticalstore.models.CompaniesAdresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompaniesAdressesRepository extends JpaRepository<CompaniesAdresses, Long> {


    @Transactional //spring
    @Modifying //spring
    @Query("delete from CompaniesAdresses a where a.id = ?1")
    int deleteCompaniesAdressesById(Long id);

    @Query("select a from CompaniesAdresses  a where a.id = ?1")
    CompaniesAdresses findCompaniesAdressesById(Long id);

}
