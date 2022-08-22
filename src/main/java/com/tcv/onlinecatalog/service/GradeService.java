package com.tcv.onlinecatalog.service;

import com.tcv.onlinecatalog.model.Grade;
import com.tcv.onlinecatalog.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;

    public void addGrade(Grade grade){
        gradeRepository.save(grade);
    }

    public List<Grade> getAllGrades(){
        return gradeRepository.findAll();
    }
}
