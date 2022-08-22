package com.tcv.onlinecatalog.repository;

import com.tcv.onlinecatalog.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
