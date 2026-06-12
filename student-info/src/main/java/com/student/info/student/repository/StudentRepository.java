package com.student.info.student.repository;


import com.student.info.student.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    Optional<StudentEntity> findByName(String name);
}
