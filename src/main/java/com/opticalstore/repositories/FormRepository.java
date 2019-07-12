package com.opticalstore.repositories;

import com.opticalstore.models.Form;
import com.opticalstore.models.GlassesMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
}
