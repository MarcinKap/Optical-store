package com.opticalstore.repositories;


import com.opticalstore.models.Glasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GlassRepository extends JpaRepository<Glasses, Long> {

    @Query("select g from Glasses g where g.glassesNumber = ?1")
        //JPQL
    Glasses findGlassesByNumber(String glassesNumber);

//    @Query("select g from Glasses g where " +
//            "g.glassesForm like %?1% " +
//            "or " +
//            "g.glassesGender like %?1% " +
//            "or " +
//            "g.glassesMark like %?1%")
//        //JPQL
//    List<Glasses> findGlassesByParam(String param);

    @Transactional //spring
    @Modifying //spring
    @Query("delete from Glasses g where g.glassesNumber = ?1")
    int deleteGlassesByNumber(int glassesNumber);





}
