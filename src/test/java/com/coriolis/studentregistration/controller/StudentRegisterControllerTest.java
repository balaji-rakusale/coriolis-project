package com.coriolis.studentregistration.controller;

import com.coriolis.studentregistration.entity.Course;
import com.coriolis.studentregistration.entity.Student;
import com.coriolis.studentregistration.service.CourseRegisterService;
import com.coriolis.studentregistration.service.StudentRegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(StudentRegisterController.class)
class StudentRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRegisterService studentRegisterService;

    private Student student;
    @BeforeEach
    void setUp() {
        student=Student.builder()
                .studentId(1L)
                .studentName("John")
                .build();
    }

    @DisplayName("JUnit test for addStudent method in the controller")
    @Test
    void addStudent() throws Exception {
        Student studentInput=Student.builder()
                .studentId(1L)
                .studentName("John")
                .build();
        Mockito.when(studentRegisterService.addStudent(studentInput)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.post("/student/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"studentId\":1,\"studentName\":\"John\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @DisplayName("JUnit test for getStudentById method in the controller")
    @Test
    void getStudentById() throws Exception {
        Mockito.when(studentRegisterService.getStudentById(1L)).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentId").value(student.getStudentId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentName").value(student.getStudentName()));

    }
}