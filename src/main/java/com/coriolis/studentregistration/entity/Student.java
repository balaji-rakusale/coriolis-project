package com.coriolis.studentregistration.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue
    private Long studentId;

    @NotNull(message = "Student should not be null")
    private String studentName;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(

            name = "student-course",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "courseID")

    )
    @NotNull(message = "Please add at least one subject ")
    private Set<Course> courses = new HashSet<>();;





}
