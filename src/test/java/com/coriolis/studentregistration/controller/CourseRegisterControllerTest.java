package com.coriolis.studentregistration.controller;

import ch.qos.logback.classic.spi.TurboFilterList;
import com.coriolis.studentregistration.entity.Course;
import com.coriolis.studentregistration.service.CourseRegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CourseRegisterController.class)
class CourseRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseRegisterService courseRegisterService;

    private Course course;
    @BeforeEach
    void setUp() {
        course=Course.builder()
                .courseId(1L)
                .courseName("Java")
                .build();
    }

    @DisplayName("JUnit test for getCourseById method in the controller")
    @Test
    void getCourseById() throws Exception {
        Mockito.when(courseRegisterService.getCourseById(1L)).thenReturn(course);
        mockMvc.perform(MockMvcRequestBuilders.get("/course/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseId").value(course.getCourseId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseName").value(course.getCourseName()));

    }

    @DisplayName("JUnit test for addCourse method in the controller")
    @Test
    void addCourse() throws Exception {
        Course courseInput=Course.builder()
                .courseId(1L)
                .courseName("Java")
                .build();
        Mockito.when(courseRegisterService.addCourse(courseInput)).thenReturn(course);

        mockMvc.perform(MockMvcRequestBuilders.post("/course/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"courseId\":1,\"courseName\":\"Java\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }
}