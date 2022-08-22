package com.tcv.onlinecatalog.controller;

import com.tcv.onlinecatalog.exception.NoCourseException;
import com.tcv.onlinecatalog.exception.NoProfessorException;
import com.tcv.onlinecatalog.model.Course;
import com.tcv.onlinecatalog.model.Professor;
import com.tcv.onlinecatalog.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("professor")
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping("courses/{profId}")
    public List<Course> getAllCoursesByProfessor(@PathVariable Integer profId) throws NoProfessorException {
        return professorService.getAllCoursesByProfessor(profId);
    }

    @PostMapping("add/professor")
    public void addProfessor(@RequestBody Professor professor) {
        professorService.addProfessor(professor);
    }

    @PostMapping("add/course-to-professor/{professorId}/{courseId}")
    public void addCourseToProfessor(@PathVariable Integer professorId, @PathVariable Integer courseId) throws NoProfessorException, NoCourseException {
        professorService.addCourseToProfessor(professorId, courseId);
    }
}
