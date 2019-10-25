package com.ing.tech.service;

import com.ing.tech.exception.CourseNotFoundException;
import com.ing.tech.model.dao.Course;
import com.ing.tech.model.dao.Teacher;
import com.ing.tech.model.dto.CourseDTO;
import com.ing.tech.model.dto.CourseList;
import com.ing.tech.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@Service
public class CourseService {

    private TeacherService teacherService;
    private CourseRepository courseRepository;

    public CourseService(TeacherService teacherService, CourseRepository courseRepository) {
        this.teacherService = teacherService;
        this.courseRepository = courseRepository;
    }

    public CourseDTO addCourse(CourseDTO courseDTO) {
        Teacher teacher = teacherService.identifyTeacher(courseDTO.getTeacherPhoneNumber());
        Course course = mapCourseDTOtoDAO(courseDTO);
        course.setTeacher(teacher);
        Course savedCourse = courseRepository.save(course);

        return mapCourseDAOtoDTO(savedCourse);
    }

    public CourseList getAllCourses(Optional<String> domain) {
        List<Course> courses =
                domain.isPresent() ? courseRepository.findAllByDomain(domain) : courseRepository.findAll();
        List<CourseDTO> courseDTOList = courses.stream().map(this::mapCourseDAOtoDTO).collect(Collectors.toList());

        return new CourseList(courseDTOList);
    }

    Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
    }

    private Course mapCourseDTOtoDAO(CourseDTO courseDTO) {
        return new Course(null, courseDTO.getName(), courseDTO.getDetails(), courseDTO.getLocation(),
                courseDTO.getDateTime(), courseDTO.getDuration(), courseDTO.getMaxParticipants(),
                courseDTO.getDomain(), 0, null, null);
    }

    private CourseDTO mapCourseDAOtoDTO(Course course) {
        return new CourseDTO(course.getId(), course.getName(), course.getDetails(), course.getLocation(),
                course.getDateTime(), course.getDuration(), course.getMaxParticipants(), course.getDomain(),
                course.getTeacher().getPhoneNumber(), course.getParticipants());
    }

}
