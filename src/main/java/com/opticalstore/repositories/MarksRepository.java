package com.opticalstore.repositories;

import com.opticalstore.models.Glasses;
import com.opticalstore.models.GlassesMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MarksRepository extends JpaRepository<GlassesMark, Long> {

    @Transactional //spring
    @Modifying //spring
    @Query("delete from GlassesMark m where m.mark = ?1")
    int deleteMarkByName(String mark);
}
