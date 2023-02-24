package com.coriolis.studentregistration.service;

import com.coriolis.studentregistration.entity.Course;
import com.coriolis.studentregistration.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseRegisterService {

    public List<Course> getCourses();

    Course getCourseById(Long id);

    Course addCourse(Course course);
}
