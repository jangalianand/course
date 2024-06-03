package com.ajtech.cource.service;


import com.ajtech.cource.entity.Course;
import com.ajtech.cource.entity.Student;
import com.ajtech.cource.repo.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CourseService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        log.info("getAllCourses(-) started");
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        log.info("getCourseById(-) started");
        return courseRepository.findById(id).orElse(null);
    }

    public Course saveCourse(Course course) {
        log.info("saveCourse(-) started");
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        log.info("deleteCourse(-) started");
        courseRepository.deleteById(id);
    }


    public List<Student> getAllStudentsFromStudentService() {
        log.info("getAllStudentsFromStudentService(-) started");
        String url = "http://localhost:9090/students";
        Student[] students = restTemplate.getForObject(url, Student[].class);
        log.info("studets :"+students);
        return Arrays.asList(students);
    }
}

