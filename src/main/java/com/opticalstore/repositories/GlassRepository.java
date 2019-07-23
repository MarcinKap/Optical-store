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
    Glasses findGlassesByNumber(int glassesNumber);

    @Query
            ("select g from Glasses g where " +
                    "g.glassesType = ?1 " +
                    "or " +
                    "g.glassesGender = ?1 " +
                    "or " +
                    "g.form = ?1 " +
                    "or " +
                    "g.price = ?1 " +
                    "or " +
                    "g.polarization = ?1 " +
                    "or " +
                    "g.widthOfTheLens = ?1 " +
                    "or " +
                    "g.glassesMarks = ?1")
        //JPQL
    List<Glasses> findGlassesByParam(String glassesType, String glassesGender, String form, double price, boolean polarization, int widthOfTheLens, String glassesMarks);


    @Transactional //spring
    @Modifying //spring
    @Query("delete from Glasses g where g.glassesNumber = ?1")
    int deleteGlassesByNumber(int glassesNumber);


}
