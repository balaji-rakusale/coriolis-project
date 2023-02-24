package com.coriolis.studentregistration.controller;

import com.coriolis.studentregistration.entity.Course;
import com.coriolis.studentregistration.entity.Student;
import com.coriolis.studentregistration.service.CourseRegisterService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  It is controller which is responsible for handling request and provide courses list, course by given id
 *  and also add new course
 */
@RestController
public class CourseRegisterController {

    private final Logger LOGGER= LoggerFactory.getLogger(CourseRegisterController.class);
    @Autowired
    private CourseRegisterService courseRegisterService;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses()
    {
        LOGGER.info("Inside getAllCourses of CourseRegisterController");
        return new ResponseEntity<>(courseRegisterService.getCourses(), HttpStatus.OK);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id)
    {
        LOGGER.info("Inside getCourseById of CourseRegisterController");
        return new ResponseEntity<>(courseRegisterService.getCourseById(id),HttpStatus.OK);
    }
    @PostMapping("/course/add")
    public ResponseEntity<Course> addCourse(@RequestBody @Valid Course course ){
        LOGGER.info("Inside addCourse of CourseRegisterController");
        return new ResponseEntity<>(courseRegisterService.addCourse(course),HttpStatus.CREATED);
    }
}
