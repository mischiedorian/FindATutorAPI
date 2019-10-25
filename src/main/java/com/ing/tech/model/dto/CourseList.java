package com.ing.tech.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseList {
    private List<CourseDTO> courses;
}
