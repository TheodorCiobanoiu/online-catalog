package com.tcv.onlinecatalog.model;

import com.sun.istack.NotNull;
import com.tcv.onlinecatalog.exception.NoGradeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotNull
    String studentName;
    @OneToMany
    List<Grade> grades = new ArrayList<Grade>();

    @ManyToOne
    Specialty specialty;


    public Integer getAnnualAverageGrade() throws NoGradeException {
        if(grades.isEmpty())
            throw new NoGradeException();
        else {
            Integer averageGrade = 0;
            for(Grade grade : grades){
                averageGrade += grade.getGrade();
            }
            averageGrade /= grades.size();
            return averageGrade;
        }

    }

}
