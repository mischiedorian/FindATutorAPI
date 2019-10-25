package com.ing.tech;

import com.ing.tech.model.dao.Course;
import com.ing.tech.model.dao.Student;
import com.ing.tech.model.dao.Teacher;
import com.ing.tech.repository.CourseRepository;
import com.ing.tech.repository.StudentRepository;
import com.ing.tech.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FindATutorApplication implements CommandLineRunner {

    public static final String APP_URL = "http://localhost:8090/";

    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public FindATutorApplication(TeacherRepository teacherRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(FindATutorApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        Teacher teacher = teacherRepository.save(new Teacher("1234", "Eduard", "Mihai", "ok"));
        teacherRepository.save(new Teacher("12345", "Dorian", "Mischie", "vok"));
        studentRepository.save(new Student("1234", "Eduard", "manda", "ACS", null));
//
        courseRepository.save(new Course(null, "PC", "progr", "ACS", 1234,
                        12, 12, "it", 0, teacher, null));
    }
}
