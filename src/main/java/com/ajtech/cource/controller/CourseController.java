package com.ajtech.cource.controller;

import com.ajtech.cource.entity.Course;
import com.ajtech.cource.entity.Student;
import com.ajtech.cource.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        log.info("getAllCourses(-) started");
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        log.info("getCourseById(-) started");
        return courseService.getCourseById(id);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        log.info("createCourse(-) started");
        return courseService.saveCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        log.info("updateCourse(-) started");
        Course existingCourse = courseService.getCourseById(id);
        if (existingCourse != null) {
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setCourseFee(course.getCourseFee());
            return courseService.saveCourse(existingCourse);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        log.info("deleteCourse(-) started");
        courseService.deleteCourse(id);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return courseService.getAllStudentsFromStudentService();
    }
}

