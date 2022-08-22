package com.tcv.onlinecatalog.controller;


import com.tcv.onlinecatalog.exception.NoGradeException;
import com.tcv.onlinecatalog.exception.NoStudentException;
import com.tcv.onlinecatalog.model.Grade;
import com.tcv.onlinecatalog.model.Student;
import com.tcv.onlinecatalog.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("add")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PutMapping("grades/add/{studentId}/{gradeId}")
    public void addGrade(@PathVariable Integer studentId, @PathVariable Integer gradeId) throws NoStudentException, NoGradeException {

        studentService.addGradeToStudent(studentId, gradeId);
    }

    @GetMapping("grades/{studentId}")
    public List<Grade> getAllGradesByStudent(@PathVariable Integer studentId) throws NoStudentException {
        return studentService.getAllGradesByStudent(studentId);
    }


    @GetMapping("all/grade-greater-than-eight")
    public List<Student> getAllStudentsGradeGreaterThanEight() {
        return studentService.getAllStudentsGradeGreaterThanEight();
    }

    @GetMapping("biggest-grade")
    public Student getStudentWithBiggestGrade() throws NoStudentException {
        return studentService.getStudentWithBiggestGrade();
    }

}
