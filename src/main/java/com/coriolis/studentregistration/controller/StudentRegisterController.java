package com.coriolis.studentregistration.controller;

import com.coriolis.studentregistration.entity.Student;
import com.coriolis.studentregistration.service.StudentRegisterService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  It is controller which is responsible for handling request and provide student list, student by given id
 *  and also add new student and mapping for register student with courses.
 */
@RestController
public class StudentRegisterController {

    private final Logger LOGGER= LoggerFactory.getLogger(StudentRegisterController.class);
    @Autowired
    private StudentRegisterService studentRegisterService;
    @PostMapping("/student/add")
    public ResponseEntity<Student> addStudent(@RequestBody  @Valid  Student student ){

        LOGGER.info("Inside addStudent of StudentRegisterController");
        return new ResponseEntity<> (studentRegisterService.addStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){

        LOGGER.info("Inside getAllStudents of StudentRegisterController");
        return new ResponseEntity <> (studentRegisterService.getAllStudent(),HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id)
    {
        LOGGER.info("Inside getStudentById of StudentRegisterController");
        return new ResponseEntity<>(studentRegisterService.getStudentById(id),HttpStatus.OK);
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<Student> registerForCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        LOGGER.info("Inside registerForCourse of StudentRegisterController");
        return new ResponseEntity <> (studentRegisterService.registerForCourse(studentId, courseId),HttpStatus.OK);
    }


    }
