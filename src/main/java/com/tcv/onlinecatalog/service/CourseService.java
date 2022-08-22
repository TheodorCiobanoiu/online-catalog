package com.tcv.onlinecatalog.service;

import com.tcv.onlinecatalog.model.Course;
import com.tcv.onlinecatalog.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public List<Course> getAllCoursesWithMoreThanThreeCreditPoints() {
        return courseRepository.findAll().stream()
                .filter(e -> e.getCreditPoints() >= 3)
                .collect(Collectors.toList());
    }

}
