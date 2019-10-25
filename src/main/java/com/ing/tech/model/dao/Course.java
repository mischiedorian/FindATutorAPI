package com.ing.tech.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
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
        return getDateTime() == course.getDateTime() &&
                getDuration() == course.getDuration() &&
                getMaxParticipants() == course.getMaxParticipants() &&
                getParticipants() == course.getParticipants() &&
                getId().equals(course.getId()) &&
                getName().equals(course.getName()) &&
                getDetails().equals(course.getDetails()) &&
                getLocation().equals(course.getLocation()) &&
                getDomain().equals(course.getDomain()) &&
                getTeacher().equals(course.getTeacher());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDetails(), getLocation(), getDateTime(), getDuration(), getMaxParticipants(), getDomain(), getParticipants(), getTeacher());
    }
}
