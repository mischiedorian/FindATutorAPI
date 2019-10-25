package com.ing.tech.repository;

import com.ing.tech.model.dao.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
}
