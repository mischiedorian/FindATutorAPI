package com.ing.tech.service;

import com.ing.tech.exception.CourseNotFoundException;
import com.ing.tech.exception.MaxParticipantsReachedException;
import com.ing.tech.exception.StudentNotFoundException;
import com.ing.tech.model.dao.Course;
import com.ing.tech.model.dao.Student;
import com.ing.tech.model.dto.CourseDTO;
import com.ing.tech.model.dto.CourseIdentifier;
import com.ing.tech.model.dto.CourseList;
import com.ing.tech.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public void withdraw(String phoneNumber, Long idCourse) {
        Student student = identifyStudent(phoneNumber);
        Course course = courseService.getCourseById(idCourse);

        if(!student.getCourses().contains(course)) {
            throw new CourseNotFoundException(String.format("Student don't enrolled on course with id %d", idCourse));
        }

        course.setParticipants(course.getParticipants() - 1);
        student.getCourses().remove(course);
        course.getStudentSet().remove(student);
    }

    public Student identifyStudent(String phoneNumber) {
        return studentRepository.findById(phoneNumber).orElseThrow(StudentNotFoundException::new);
    }

    public CourseList getAllCourses(String phoneNumber) {
        Student student = identifyStudent(phoneNumber);
        List<CourseDTO> courses = student.getCourses().stream().map((c) -> courseService.mapCourseDAOtoDTO(c)).collect(Collectors.toList());
        return new CourseList(courses);
    }
}
