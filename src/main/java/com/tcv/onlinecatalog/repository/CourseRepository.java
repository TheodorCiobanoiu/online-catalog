package com.tcv.onlinecatalog.repository;

import com.tcv.onlinecatalog.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
