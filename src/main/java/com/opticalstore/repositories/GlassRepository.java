package com.opticalstore.repositories;


import com.opticalstore.models.Glasses;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GlassRepository extends JpaRepository<Glasses, Long> {


    @Query("select g from Glasses g where g.glassesNumber = ?1")
    Glasses findGlassesByNumber(Long glassesNumber);

    @Query("select g from Glasses g where g.glassesType = ?1")
    List<Glasses> findGlassesByType(String glassesType);

    @Query("select g from Glasses g where g.glassesType = ?1")
    List<Glasses> findGlassesByTypeAndSort(String glassesType, Sort sort);

    @Query("select g from Glasses g where g.glassesType = ?1 order by ?2 asc")
    List<Glasses> findGlassesByTypeAndSortAscending( String glassesType, String orderBy);

    @Query("select g from Glasses g where g.glassesType = ?1 order by ?2 desc ")
    List<Glasses> findGlassesByTypeAndSortDescending(String glassesType, String orderBy);

    @Query("select g from Glasses g where g.id = ?1")
    Glasses findGlassesById(Long id);





    @Query
            ("select g from Glasses g where " +
                    "(g.glassesType= ?1 or ?1 = '' )" +
                    "and " +
                    "(?2 is null or g.glassesGender = ?2) "
                    +
                    "AND " +
                    "(?3 = '' or g.form = ?3) "
                    +
                    "AND " +
                    "(?4 = 0.0 or g.price >= ?4) "
                    +
                    "AND " +
                    "(?5 = 0.0 or g.price <= ?5) "
                    +
                    "AND " +
                    "(?6 is null or g.polarization = ?6) "
                    +
                    "AND " +
                    "(?7 = 0 or g.widthOfTheLens >= ?7) "
                    +
                    "AND " +
                    "(?8 = 0 or g.widthOfTheLens <= ?8) "
                    +
                    "AND " +
                    "(?9 = '' or g.glassesMarks = ?9)"
            )
    List<Glasses> findGlassesByParam(String glassesType,
                                     String glassesGender,
                                     String form,
                                     double priceLowerLimit,
                                     double priceUpperLimit,
                                     Boolean polarization,
                                     int widthOfTheLensLowerLimit,
                                     int widthOfTheLensUpperLimit,
                                     String glassesMarks);

    @Transactional
    @Modifying
    @Query("delete from Glasses g where g.id = ?1")
    int deleteGlassesById(Long id);



//    @Query("select g from g Glasses ORDER BY param ")
//    List<Glasses> sortGlassesByParam(String param);

}
