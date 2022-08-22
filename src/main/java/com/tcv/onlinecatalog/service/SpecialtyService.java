package com.tcv.onlinecatalog.service;

import com.tcv.onlinecatalog.exception.NoCourseException;
import com.tcv.onlinecatalog.exception.NoGradeException;
import com.tcv.onlinecatalog.exception.NoSpecialtyException;
import com.tcv.onlinecatalog.exception.NoStudentException;
import com.tcv.onlinecatalog.model.Course;
import com.tcv.onlinecatalog.model.Specialty;
import com.tcv.onlinecatalog.model.Student;
import com.tcv.onlinecatalog.repository.CourseRepository;
import com.tcv.onlinecatalog.repository.SpecialtyRepository;
import com.tcv.onlinecatalog.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public void addSpecialty(Specialty specialty) {
        specialtyRepository.save(specialty);
    }

    public void addStudentToSpecialty(Integer studentId, Integer specialtyId) throws NoSpecialtyException, NoStudentException {

        Optional<Specialty> optionalSpecialty = specialtyRepository.findById(specialtyId);
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if (!optionalSpecialty.isPresent()) {
            throw new NoSpecialtyException();
        } else if (!optionalStudent.isPresent()) {
            throw new NoStudentException();
        } else {
            optionalSpecialty.get().addStudent(optionalStudent.get());
            optionalStudent.get().setSpecialty(optionalSpecialty.get());
            studentRepository.save(optionalStudent.get());
            specialtyRepository.save(optionalSpecialty.get());
        }
    }

    public void addCourseToSpecialty(Integer courseId, Integer specialtyId) throws NoSpecialtyException, NoCourseException {

        Optional<Specialty> optionalSpecialty = specialtyRepository.findById(specialtyId);
        Optional<Course> optionalCourse = courseRepository.findById(courseId);

        if (!optionalSpecialty.isPresent()) {
            throw new NoSpecialtyException();
        } else if (!optionalCourse.isPresent()) {
            throw new NoCourseException();
        } else {
            optionalSpecialty.get().addCourse(optionalCourse.get());
            specialtyRepository.save(optionalSpecialty.get());
        }
    }


    public List<Student> getAllStudentsBySpecialty(Integer specialtyId) throws NoSpecialtyException {

        Optional<Specialty> optionalSpecialty = specialtyRepository.findById(specialtyId);

        if (!optionalSpecialty.isPresent()) throw new NoSpecialtyException();
        else {
            return optionalSpecialty.get().getStudents();
        }
    }

    public Specialty getSpecialtyWithMostStudent() throws NoSpecialtyException {
        Optional<Specialty> optionalSpecialty = specialtyRepository.findAll().stream()
                .max(Comparator.comparingInt(x -> x.getStudents().size()));
        if (optionalSpecialty.isPresent())
            return optionalSpecialty.get();
        else throw new NoSpecialtyException();
    }


    public Integer getAverageSpecialtyGrade(Integer specialtyId) throws NoSpecialtyException {
        Optional<Specialty> optionalSpecialty = specialtyRepository.findById(specialtyId);
        if (!optionalSpecialty.isPresent()) {
            throw new NoSpecialtyException();
        } else {
            return optionalSpecialty.get().getStudents().stream().map(x -> {
                try {
                    return x.getAnnualAverageGrade();
                } catch (NoGradeException e) {
                    throw new RuntimeException(e);
                }
            }).reduce(0, Integer::sum) / optionalSpecialty.get().getStudents().size();
        }
    }

    public Student getStudentWithBiggestGrade(Integer specialtyId) throws NoStudentException, NoSpecialtyException {

        Optional<Specialty> optionalSpecialty = specialtyRepository.findById(specialtyId);

        if (optionalSpecialty.isPresent()) {

            Optional<Student> optionalStudent = optionalSpecialty.get().getStudents().stream()
                    .max(Comparator.comparingInt(x -> {
                        try {
                            return x.getAnnualAverageGrade();
                        } catch (NoGradeException e) {
                            throw new RuntimeException(e);
                        }
                    }));

            if (optionalStudent.isPresent()) return optionalStudent.get();
            else throw new NoStudentException();

        } else throw new NoSpecialtyException();
    }
}
