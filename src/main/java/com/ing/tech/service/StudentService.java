package com.ing.tech.service;

import com.ing.tech.exception.MaxParticipantsReachedException;
import com.ing.tech.exception.StudentNotFoundException;
import com.ing.tech.model.dao.Course;
import com.ing.tech.model.dao.Student;
import com.ing.tech.model.dto.CourseIdentifier;
import com.ing.tech.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private CourseService courseService;
    private StudentRepository studentRepository;

    public StudentService(CourseService courseService, StudentRepository studentRepository) {
        this.courseService = courseService;
        this.studentRepository = studentRepository;
    }

    public Student insertStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public void enroll(String phoneNumber, CourseIdentifier courseIdentifier) {
        Student student = identifyStudent(phoneNumber);
        Course course = courseService.getCourseById(courseIdentifier.getIdCourse());
        int numberOfParticipants = course.getParticipants();

        if (numberOfParticipants == course.getMaxParticipants()) {
            throw new MaxParticipantsReachedException();
        }

        course.setParticipants(numberOfParticipants + 1);
        student.getCourses().add(course);
    }

    public Student identifyStudent(String phoneNumber) {
        return studentRepository.findById(phoneNumber).orElseThrow(StudentNotFoundException::new);
    }

}
