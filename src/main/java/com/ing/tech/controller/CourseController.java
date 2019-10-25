package com.ing.tech.controller;

import com.ing.tech.model.dto.CourseDTO;
import com.ing.tech.model.dto.CourseList;
import com.ing.tech.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("courses")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO addedCourse = courseService.addCourse(courseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(addedCourse);
    }

    @GetMapping
    public ResponseEntity<CourseList> getAllCourses(@RequestParam Optional<String> domain) {
        return ResponseEntity.ok(courseService.getAllCourses(domain));
    }
}
