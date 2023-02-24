package com.coriolis.studentregistration.service.impl;

import com.coriolis.studentregistration.entity.Course;
import com.coriolis.studentregistration.entity.Student;
import com.coriolis.studentregistration.exception.CourseException;
import com.coriolis.studentregistration.exception.StudentException;
import com.coriolis.studentregistration.repository.CourseRepository;
import com.coriolis.studentregistration.repository.StudentRepository;
import com.coriolis.studentregistration.service.StudentRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  It is service class which take studentRepository as a dependency and perform database
 *  related operation
 */
@Service
public class StudentRegisterServiceImpl implements StudentRegisterService {

    private final Logger LOGGER= LoggerFactory.getLogger(StudentRegisterServiceImpl.class);
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Student addStudent(Student student) {
        LOGGER.info("Inside addStudent method ofStudentRegisterServiceImpl");
        return studentRepository.save(student);


    }

    @Override
    public List<Student> getAllStudent() {
        LOGGER.info("Inside getAllStudent method ofStudentRegisterServiceImpl");
        List<Student> studentList=studentRepository.findAll();
        if(studentList!=null) {
            return studentRepository.findAll();
        }
        else {
            throw new StudentException("No Student found");
        }
    }

    @Override
    public Student getStudentById(Long id) {
        LOGGER.info("Inside getStudentById method ofStudentRegisterServiceImpl");
        return studentRepository.findById(id).orElseThrow(
                () -> new StudentException("No Student is present with " +id));

    }

    @Override
    public Student registerForCourse(Long studentId, Long courseId) {
        LOGGER.info("Inside registerForCourse method ofStudentRegisterServiceImpl");
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseException("Course not found"));
        student.getCourses().add(course);
        return studentRepository.save(student);
        }
}
