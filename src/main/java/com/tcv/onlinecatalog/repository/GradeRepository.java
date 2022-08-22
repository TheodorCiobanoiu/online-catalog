package com.tcv.onlinecatalog.repository;

import com.tcv.onlinecatalog.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
}
