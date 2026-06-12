package com.education_info.repository;

import com.education_info.entity.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<EducationEntity,Long> {
    List<EducationEntity> findByStudentId(Long studentId);
}
