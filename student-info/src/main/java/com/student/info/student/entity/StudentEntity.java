package com.student.info.student.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "student")
@Setter
@Getter
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @CreationTimestamp
    LocalDateTime createTimeStamp;
    @JsonManagedReference
    @OneToMany(mappedBy = "student" , orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CourseEntity> course = new ArrayList<>();
}
