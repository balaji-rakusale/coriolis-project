package com.coriolis.studentregistration.service.impl;

import com.coriolis.studentregistration.entity.Course;
import com.coriolis.studentregistration.entity.Student;
import com.coriolis.studentregistration.exception.CourseException;
import com.coriolis.studentregistration.exception.StudentException;
import com.coriolis.studentregistration.repository.StudentRepository;
import com.coriolis.studentregistration.service.StudentRegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRegisterServiceImplTest {

    @Autowired
    private StudentRegisterService studentRegisterService;

    @MockBean
    private StudentRepository studentRepository;

    Student student;

    @BeforeEach
    void setUp() {
        student= Student.builder()
                .studentId(1L)
                .studentName("John")
                .build();
    }

    @DisplayName("JUnit test for addStudent method")
    @Test
    void addStudent() {
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        assertEquals(student, studentRegisterService.addStudent(student));
    }

    @DisplayName("JUnit test for getAllStudent method")
    @Test
    void getAllStudent() {
        List<Student> studentList= Arrays.asList(student);
        Mockito.when(studentRepository.findAll()).thenReturn(studentList);
        List<Student> foundCourses=studentRegisterService.getAllStudent();
        assertEquals(studentList.size(),foundCourses.size());
        assertEquals(studentList.get(0).getStudentName(),foundCourses.get(0).getStudentName());
    }

    @DisplayName("JUnit test for getStudentById method")
    @Test
    void whenValidStudentID_getStudentById() {
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Student foundStudent=studentRegisterService.getStudentById(1L);
        assertEquals(1L,foundStudent.getStudentId());
        assertEquals("John",foundStudent.getStudentName());
    }

    @DisplayName("JUnit test for getStudentById method if Student not found")
    @Test()
    void When_getStudentById_ThrowsCourseException(){
        Mockito.when(studentRepository.findById(2L)).thenReturn(Optional.of(student));
        assertThrows(StudentException.class, ()->studentRegisterService.getStudentById(null));
    }
}