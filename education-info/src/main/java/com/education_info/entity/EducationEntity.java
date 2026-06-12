package com.education_info.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "education")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String university;
    String degree;
    Long studentId;
}
