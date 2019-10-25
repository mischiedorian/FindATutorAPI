package com.ing.tech.controller;

import com.ing.tech.FindATutorApplication;
import com.ing.tech.model.dao.Student;
import com.ing.tech.model.dto.CourseIdentifier;
import com.ing.tech.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student addedStudent = studentService.insertStudent(student);

        String resourceLocation = FindATutorApplication.APP_URL + addedStudent.getPhoneNumber();
        return ResponseEntity.created(URI.create(resourceLocation))
                .body(addedStudent);
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<Student> identifyStudent(@PathVariable String phoneNumber) {
        Student student = studentService.identifyStudent(phoneNumber);

        return ResponseEntity.ok(student);
    }

//    POST /students/{phoneNumber}/courses  => 200 OK if the max number of participants is not reached and 400 if the max is already reached
//    {
//        idCourse
//    }
    @PostMapping("/{phoneNumber}/courses")
    public ResponseEntity attendCourse(@PathVariable String phoneNumber, @RequestBody CourseIdentifier courseIdentifier) {
        studentService.enroll(phoneNumber, courseIdentifier);

        return ResponseEntity.ok().build();
    }

//    @GetMapping("/{phoneNumber}/courses")
//    public ResponseEntity getAllCourses(@PathVariable String phoneNumber) {
//        studentService.enroll(phoneNumber);
//
//        return ResponseEntity.ok().build();
//    }

}
