package com.ing.tech.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter @Setter
public class Course {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private String details;
    private String location;
    private long dateTime;
    private int duration;
    private int maxParticipants;
    private String domain;
    private int participants;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="phoneNumber")
    private Teacher teacher;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    Set<Student> studentSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return getId().equals(course.getId());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
