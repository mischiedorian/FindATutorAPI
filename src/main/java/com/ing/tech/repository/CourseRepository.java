package com.ing.tech.repository;

import com.ing.tech.model.dao.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByDomain(Optional<String> domain);
}
