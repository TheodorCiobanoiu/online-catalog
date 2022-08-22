package com.tcv.onlinecatalog.controller;


import com.tcv.onlinecatalog.model.Course;
import com.tcv.onlinecatalog.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("add")
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    @GetMapping("all/greater-than-three-credit-points")
    public List<Course> getAllCoursesWithMoreThanThreeCreditPoints() {
        return courseService.getAllCoursesWithMoreThanThreeCreditPoints();
    }
}
