package com.tcv.onlinecatalog.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotNull
    String subject;
    @NotNull
    Integer creditPoints;
    @ManyToOne
    Professor professor;
    @ManyToOne
    Specialty specialty;

}
