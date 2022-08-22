package com.tcv.onlinecatalog.controller;

import com.tcv.onlinecatalog.exception.NoCourseException;
import com.tcv.onlinecatalog.exception.NoSpecialtyException;
import com.tcv.onlinecatalog.exception.NoStudentException;
import com.tcv.onlinecatalog.model.Specialty;
import com.tcv.onlinecatalog.model.Student;
import com.tcv.onlinecatalog.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("specialty")
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @GetMapping("{specialtyId}/students")
    public List<Student> getAllStudentsBySpecialty(@PathVariable Integer specialtyId) throws NoSpecialtyException {
        return specialtyService.getAllStudentsBySpecialty(specialtyId);
    }

    @GetMapping("most-students")
    public Specialty getSpecialtyWithMostStudents() throws NoSpecialtyException {
        return specialtyService.getSpecialtyWithMostStudent();
    }

    @GetMapping("{id}/average-grade")
    public Integer getAverageSpecialtyGrade(@PathVariable Integer id) throws NoSpecialtyException {
        return specialtyService.getAverageSpecialtyGrade(id);
    }

    @GetMapping("{id}/student-with-biggest-grade")
    public Student getStudentWithBiggestGrade(@PathVariable Integer id) throws NoStudentException, NoSpecialtyException {
        return specialtyService.getStudentWithBiggestGrade(id);
    }

    @PostMapping("add/specialty")
    public void addSpecialty(@RequestBody Specialty specialty) {
        specialtyService.addSpecialty(specialty);
    }

    @PostMapping("add/student-to-specialty/{studentId}/{specialtyId}")
    public void addStudentToSpecialty(@PathVariable Integer studentId, @PathVariable Integer specialtyId) throws NoStudentException, NoSpecialtyException {
        specialtyService.addStudentToSpecialty(studentId, specialtyId);
    }

    @PostMapping("add/course-to-specialty/{studentId}/{courseId}")
    public void addCourseToSpecialty(@PathVariable Integer studentId, @PathVariable Integer courseId) throws NoSpecialtyException, NoCourseException {
        specialtyService.addCourseToSpecialty(studentId, courseId);
    }
}
