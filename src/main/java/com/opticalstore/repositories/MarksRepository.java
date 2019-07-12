package com.opticalstore.repositories;

import com.opticalstore.models.Glasses;
import com.opticalstore.models.GlassesMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarksRepository extends JpaRepository<GlassesMark, Long> {

}
