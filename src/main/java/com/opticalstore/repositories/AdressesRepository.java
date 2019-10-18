package com.opticalstore.repositories;


import com.opticalstore.models.Adresses;
import com.opticalstore.models.Form;
import com.opticalstore.models.Glasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AdressesRepository extends JpaRepository<Adresses, Long> {

    @Transactional //spring
    @Modifying //spring
    @Query("delete from Adresses a where a.id = ?1")
    int deleteAdressesById(Long id);

    @Query("select a from Adresses a where a.adressId = ?1")
        //JPQL
    Adresses findAdressById(Long adressId);


}
