package com.ing.tech.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
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
}
