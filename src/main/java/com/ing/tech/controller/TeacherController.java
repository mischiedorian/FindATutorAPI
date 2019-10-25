package com.ing.tech.controller;

import com.ing.tech.FindATutorApplication;
import com.ing.tech.model.dao.Teacher;
import com.ing.tech.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        Teacher addedTeacher = teacherService.insertTeacher(teacher);


        String resourceLocation = FindATutorApplication.APP_URL + addedTeacher.getPhoneNumber();
        return ResponseEntity.created(URI.create(resourceLocation))
                .body(addedTeacher);
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<Teacher> identifyTeacher(@PathVariable String phoneNumber) {
        Teacher teacher = teacherService.identifyTeacher(phoneNumber);

        return ResponseEntity.ok(teacher);
    }

}
