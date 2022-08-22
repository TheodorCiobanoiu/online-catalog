package com.tcv.onlinecatalog.controller;

import com.tcv.onlinecatalog.model.Grade;
import com.tcv.onlinecatalog.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("grades")
public class GradeController {

    private final GradeService gradeService;

    @PostMapping("add-grade")
    public void addGrade(@RequestBody Grade grade){
        gradeService.addGrade(grade);
    }
    @GetMapping("all")
    public List<Grade> getAllGrades(){
        return gradeService.getAllGrades();
    }
}
