package com.tcv.onlinecatalog.model;

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
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @OneToMany
    List<Course> courses = new ArrayList<Course>();
    @OneToMany
    List<Project> projects = new ArrayList<Project>();
    String firstName;
    String lastName;

    public void addCourse(Course course){
        courses.add(course);
    }

    public void addProject(Project project){
        projects.add(project);
    }


}
