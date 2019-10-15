package com.opticalstore.repositories;


import com.opticalstore.models.Adresses;
import com.opticalstore.models.Form;
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
    @Query("delete from Adresses a where a.name = ?1")
    int deleteAdressesByName(String name);


//
//    @Query("select id from user_adresses a where a.glassesNumber = ?1")
//        //JPQL
//    List<Integer> findUserAdresses(Integer glassesNumber);



}
