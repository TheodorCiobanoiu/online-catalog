package com.tcv.onlinecatalog.service;

import com.tcv.onlinecatalog.exception.NoCourseException;
import com.tcv.onlinecatalog.exception.NoProfessorException;
import com.tcv.onlinecatalog.model.Course;
import com.tcv.onlinecatalog.model.Professor;
import com.tcv.onlinecatalog.repository.CourseRepository;
import com.tcv.onlinecatalog.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;

    public void addProfessor(Professor professor) {
        professorRepository.save(professor);
    }

    public void addCourseToProfessor(Integer professorId, Integer courseId) throws NoProfessorException, NoCourseException {
        Optional<Professor> optionalProfessor = professorRepository.findById(professorId);
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (!optionalProfessor.isPresent()) {
            throw new NoProfessorException();
        } else if (!optionalCourse.isPresent()) {
            throw new NoCourseException();
        }
        optionalProfessor.get().addCourse(optionalCourse.get());
        optionalCourse.get().setProfessor(optionalProfessor.get());
        courseRepository.save(optionalCourse.get());
        professorRepository.save(optionalProfessor.get());
    }

    public List<Course> getAllCoursesByProfessor(Integer professorId) throws NoProfessorException {
        if (!professorRepository.findById(professorId).isPresent()) {
            throw new NoProfessorException();
        }
        return professorRepository.findById(professorId).get().getCourses();
    }


}
