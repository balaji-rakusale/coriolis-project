package com.coriolis.studentregistration.service.impl;

import com.coriolis.studentregistration.controller.StudentRegisterController;
import com.coriolis.studentregistration.entity.Course;
import com.coriolis.studentregistration.entity.Student;
import com.coriolis.studentregistration.exception.CourseException;
import com.coriolis.studentregistration.exception.StudentException;
import com.coriolis.studentregistration.repository.CourseRepository;
import com.coriolis.studentregistration.service.CourseRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  It is service class which take courseRepository as a dependency and perform database
 *  related operation
 */
@Service
public class CourseRegisterServiceImpl implements CourseRegisterService {

    private final Logger LOGGER= LoggerFactory.getLogger(CourseRegisterServiceImpl.class);
    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> getCourses() {
        LOGGER.info("Inside getCourses method of CourseRegisterServiceImpl");
        List<Course> courseList=courseRepository.findAll();
        if(courseList!=null) {
            return courseRepository.findAll();
        }
        else {
            throw new CourseException("No Course found");
        }
    }

    @Override
    public Course getCourseById(Long id) {

        LOGGER.info("Inside getCourseById method of CourseRegisterServiceImpl");
        return courseRepository.findById(id).orElseThrow(
                () -> new CourseException("No Course is present with " +id));

    }

    @Override
    public Course addCourse(Course course) {
        LOGGER.info("Inside addCourse method of CourseRegisterServiceImpl");
        return courseRepository.save(course);
        }


}
