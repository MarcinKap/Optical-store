package com.opticalstore.repositories;

import com.opticalstore.models.Form;
import com.opticalstore.models.GlassesMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

    @Transactional //spring
    @Modifying //spring
    @Query("delete from Form f where f.form = ?1")
    int deleteFormByName(String form);
}
