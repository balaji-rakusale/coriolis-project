package com.coriolis.studentregistration.service;

import com.coriolis.studentregistration.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentRegisterService {
    public Student addStudent(Student student);

    public List<Student> getAllStudent();

    Student getStudentById(Long id);

    Student registerForCourse(Long studentId, Long courseId);
}


