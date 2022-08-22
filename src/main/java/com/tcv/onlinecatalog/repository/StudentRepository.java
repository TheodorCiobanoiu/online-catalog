package com.tcv.onlinecatalog.repository;

import com.tcv.onlinecatalog.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
