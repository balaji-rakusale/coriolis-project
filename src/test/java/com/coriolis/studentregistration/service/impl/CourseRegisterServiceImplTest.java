package com.coriolis.studentregistration.service.impl;

import com.coriolis.studentregistration.entity.Course;
import com.coriolis.studentregistration.exception.CourseException;
import com.coriolis.studentregistration.repository.CourseRepository;
import com.coriolis.studentregistration.service.CourseRegisterService;
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

import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.Array;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRegisterServiceImplTest {

    @Autowired
    CourseRegisterService courseRegisterService;

    @MockBean
    private CourseRepository courseRepository;

    Course course;
    @BeforeEach
    void setUp(){
      course=
             Course.builder()
                     .courseId(1L)
                     .courseName("Java")
                     .build();
       }

    @DisplayName("JUnit test for getCourses method")
    @Test
    void getCourses() {

        List<Course> courseList= Arrays.asList(course);
        Mockito.when(courseRepository.findAll()).thenReturn(courseList);
        List<Course> foundCourses=courseRegisterService.getCourses();
        assertEquals(courseList.size(),foundCourses.size());
        assertEquals(courseList.get(0).getCourseName(),foundCourses.get(0).getCourseName());
    }

    @DisplayName("JUnit test for getCourseById method")
    @Test
    void whenValidCourseID_getCourseById() {
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        Course foundCourse=courseRegisterService.getCourseById(1L);
        assertEquals(1L,foundCourse.getCourseId());
        assertEquals("Java",foundCourse.getCourseName());

    }

    @DisplayName("JUnit test for getCourseById method if course not found")
    @Test()
    void When_getCourseById_ThrowsCourseException(){
        Mockito.when(courseRepository.findById(2L)).thenReturn(Optional.of(course));
        assertThrows(CourseException.class, ()->courseRegisterService.getCourseById(null));
    }

    @DisplayName("JUnit test for addCourse method")
    @Test
    void addCourse() {
        Mockito.when(courseRepository.save(course)).thenReturn(course);
        assertEquals(course, courseRegisterService.addCourse(course));

    }
}