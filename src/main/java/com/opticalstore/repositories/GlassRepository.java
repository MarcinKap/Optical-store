package com.opticalstore.repositories;


import com.opticalstore.models.Glasses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface GlassRepository extends JpaRepository<Glasses, Long> {

    @Query("select g from Glasses g where (:glassesType is null or g.glassesType = :glassesType)" +
            " and (:glassesNumber is null or :glassesNumber=0  or g.glassesNumber = :glassesNumber)" +
            " and (:glassesGender is null or g.glassesGender = :glassesGender)" +
            " and (:glassesMarks is null or :glassesMarks='' or g.glassesMarks = :glassesMarks)" +
            " and (:form is null or :form ='' or g.form = :form)" +
            " and (:polarization is null or g.polarization = :polarization)" +
            " and (:widthOfTheLensLowerLimit is null or :widthOfTheLensLowerLimit=0  or g.widthOfTheLens >= :widthOfTheLensLowerLimit)" +
            " and (:widthOfTheLensUpperLimit is null or :widthOfTheLensUpperLimit=0  or g.widthOfTheLens <= :widthOfTheLensUpperLimit)" +
            " and (:priceLowerLimit is null or :priceLowerLimit=0.0  or g.price >= :priceLowerLimit)" +
            " and (:priceUpperLimit is null or :priceUpperLimit=0.0  or g.price <= :priceUpperLimit)")
    public List<Glasses> findAllByGlassesSearchingFormWithSort(
            @Param("glassesType") Optional<String> glassesType,
            @Param("glassesNumber") Optional<Long> glassesNumber,
            @Param("glassesGender") Optional<String> glassesGender,
            @Param("glassesMarks") Optional<String> glassesMarks,
            @Param("form") Optional<String> form,
            @Param("polarization") Optional<Boolean> polarization,
            @Param("widthOfTheLensLowerLimit") Optional<Integer> widthOfTheLensLowerLimit,
            @Param("widthOfTheLensUpperLimit") Optional<Integer> widthOfTheLensUpperLimit,
            @Param("priceLowerLimit") Optional<Double> priceLowerLimit,
            @Param("priceUpperLimit") Optional<Double> priceUpperLimit, Sort sort);

    @Query("select g from Glasses g where (:glassesType is null or :glassesType ='' or g.glassesType = :glassesType)" +
            " and (:glassesNumber is null or :glassesNumber=0  or g.glassesNumber = :glassesNumber)" +
            " and (:glassesGender is null or :glassesGender = '' or  g.glassesGender = :glassesGender)" +
            " and (:glassesMarks is null or :glassesMarks='' or g.glassesMarks = :glassesMarks)" +
            " and (:form is null or :form ='' or g.form = :form)" +
            " and (:polarization is null or g.polarization = :polarization)" +
            " and (:widthOfTheLensLowerLimit is null or :widthOfTheLensLowerLimit=0  or g.widthOfTheLens >= :widthOfTheLensLowerLimit)" +
            " and (:widthOfTheLensUpperLimit is null or :widthOfTheLensUpperLimit=0  or g.widthOfTheLens <= :widthOfTheLensUpperLimit)" +
            " and (:priceLowerLimit is null or :priceLowerLimit=0.0  or g.price >= :priceLowerLimit)" +
            " and (:priceUpperLimit is null or :priceUpperLimit=0.0  or g.price <= :priceUpperLimit)")
    public List<Glasses> findAllByGlassesSearchingForm(
            @Param("glassesType") Optional<String> glassesType,
            @Param("glassesNumber") Optional<Long> glassesNumber,
            @Param("glassesGender") Optional<String> glassesGender,
            @Param("glassesMarks") Optional<String> glassesMarks,
            @Param("form") Optional<String> form,
            @Param("polarization") Optional<Boolean> polarization,
            @Param("widthOfTheLensLowerLimit") Optional<Integer> widthOfTheLensLowerLimit,
            @Param("widthOfTheLensUpperLimit") Optional<Integer> widthOfTheLensUpperLimit,
            @Param("priceLowerLimit") Optional<Double> priceLowerLimit,
            @Param("priceUpperLimit") Optional<Double> priceUpperLimit);

    @Query("select g from Glasses g where (:glassesType is null or g.glassesType = :glassesType)" +
            " and (:glassesNumber is null or :glassesNumber=0  or g.glassesNumber = :glassesNumber)" +
            " and (:glassesGender is null or g.glassesGender = :glassesGender)" +
            " and (:glassesMarks is null or :glassesMarks='' or g.glassesMarks = :glassesMarks)" +
            " and (:form is null or :form ='' or g.form = :form)" +
            " and (:polarization is null or g.polarization = :polarization)" +
            " and (:widthOfTheLensLowerLimit is null or :widthOfTheLensLowerLimit=0  or g.widthOfTheLens >= :widthOfTheLensLowerLimit)" +
            " and (:widthOfTheLensUpperLimit is null or :widthOfTheLensUpperLimit=0  or g.widthOfTheLens <= :widthOfTheLensUpperLimit)" +
            " and (:priceLowerLimit is null or :priceLowerLimit=0.0  or g.price >= :priceLowerLimit)" +
            " and (:priceUpperLimit is null or :priceUpperLimit=0.0  or g.price <= :priceUpperLimit)")
    public Page<Glasses> findAllByGlassesSearchingFormWithSortWithPaginating(
            @Param("glassesType") Optional<String> glassesType,
            @Param("glassesNumber") Optional<Long> glassesNumber,
            @Param("glassesGender") Optional<String> glassesGender,
            @Param("glassesMarks") Optional<String> glassesMarks,
            @Param("form") Optional<String> form,
            @Param("polarization") Optional<Boolean> polarization,
            @Param("widthOfTheLensLowerLimit") Optional<Integer> widthOfTheLensLowerLimit,
            @Param("widthOfTheLensUpperLimit") Optional<Integer> widthOfTheLensUpperLimit,
            @Param("priceLowerLimit") Optional<Double> priceLowerLimit,
            @Param("priceUpperLimit") Optional<Double> priceUpperLimit,
            Pageable pageable);

    @Query("select g from Glasses g where (:glassesType is null or :glassesType ='' or g.glassesType = :glassesType)" +
            " and (:glassesNumber is null or :glassesNumber=0  or g.glassesNumber = :glassesNumber)" +
            " and (:glassesGender is null or :glassesGender = '' or  g.glassesGender = :glassesGender)" +
            " and (:glassesMarks is null or :glassesMarks='' or g.glassesMarks = :glassesMarks)" +
            " and (:form is null or :form ='' or g.form = :form)" +
            " and (:polarization is null or g.polarization = :polarization)" +
            " and (:widthOfTheLensLowerLimit is null or :widthOfTheLensLowerLimit=0  or g.widthOfTheLens >= :widthOfTheLensLowerLimit)" +
            " and (:widthOfTheLensUpperLimit is null or :widthOfTheLensUpperLimit=0  or g.widthOfTheLens <= :widthOfTheLensUpperLimit)" +
            " and (:priceLowerLimit is null or :priceLowerLimit=0.0  or g.price >= :priceLowerLimit)" +
            " and (:priceUpperLimit is null or :priceUpperLimit=0.0  or g.price <= :priceUpperLimit)")
    public Page<Glasses> findAllByGlassesSearchingFormWithPaginating(
            @Param("glassesType") Optional<String> glassesType,
            @Param("glassesNumber") Optional<Long> glassesNumber,
            @Param("glassesGender") Optional<String> glassesGender,
            @Param("glassesMarks") Optional<String> glassesMarks,
            @Param("form") Optional<String> form,
            @Param("polarization") Optional<Boolean> polarization,
            @Param("widthOfTheLensLowerLimit") Optional<Integer> widthOfTheLensLowerLimit,
            @Param("widthOfTheLensUpperLimit") Optional<Integer> widthOfTheLensUpperLimit,
            @Param("priceLowerLimit") Optional<Double> priceLowerLimit,
            @Param("priceUpperLimit") Optional<Double> priceUpperLimit,
            Pageable pageable);







    @Query("select g from Glasses g where g.glassesNumber = ?1")
    Glasses findGlassesByNumber(Long glassesNumber);

    @Query("select g from Glasses g where g.glassesType = ?1")
    List<Glasses> findGlassesByType(String glassesType);

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
