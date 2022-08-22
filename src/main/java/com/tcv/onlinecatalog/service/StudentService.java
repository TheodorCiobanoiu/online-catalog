package com.tcv.onlinecatalog.service;

import com.tcv.onlinecatalog.exception.NoGradeException;
import com.tcv.onlinecatalog.exception.NoStudentException;
import com.tcv.onlinecatalog.model.Grade;
import com.tcv.onlinecatalog.model.Student;
import com.tcv.onlinecatalog.repository.GradeRepository;
import com.tcv.onlinecatalog.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void addGradeToStudent(Integer studentId, Integer gradeId) throws NoStudentException, NoGradeException {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Optional<Grade> optionalGrade = gradeRepository.findById(gradeId);
        if (!optionalStudent.isPresent())
            throw new NoStudentException();
        else if(!optionalGrade.isPresent()){
            throw new NoGradeException();
        }
        else {
            optionalStudent.get().getGrades().add(optionalGrade.get());
            studentRepository.save(optionalStudent.get());
        }
    }

    public List<Grade> getAllGradesByStudent(Integer userId) throws NoStudentException {
        if (studentRepository.findById(userId).isPresent()) {
            throw new NoStudentException();
        }
        return studentRepository.findById(userId).get().getGrades();
    }

    public List<Student> getAllStudentsGradeGreaterThanEight() {
        return studentRepository.findAll().stream()
                .filter(e -> {
                    try {
                        return e.getAnnualAverageGrade() >= 8;
                    } catch (NoGradeException ex) {
                        throw new RuntimeException(ex);
                    }
                }).collect(Collectors.toList());
    }

    public Student getStudentWithBiggestGrade() throws NoStudentException {

        Optional<Student> optionalStudent = studentRepository.findAll().stream().max(Comparator.comparingInt(x -> {
            try {
                return x.getAnnualAverageGrade();
            } catch (NoGradeException e) {
                throw new RuntimeException(e);
            }
        }));
        if (optionalStudent.isPresent())
            return optionalStudent.get();
        else throw new NoStudentException();
    }

}
