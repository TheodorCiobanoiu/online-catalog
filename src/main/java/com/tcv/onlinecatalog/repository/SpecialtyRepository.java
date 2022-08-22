package com.tcv.onlinecatalog.repository;

import com.tcv.onlinecatalog.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
}
