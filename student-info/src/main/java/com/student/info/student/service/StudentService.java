package com.student.info.student.service;

import com.student.info.student.config.EducationFeignConfig;
import com.student.info.student.dto.Coursedto;
import com.student.info.student.dto.Educationdto;
import com.student.info.student.dto.Studentdto;
import com.student.info.student.entity.CourseEntity;
import com.student.info.student.entity.StudentEntity;
import com.student.info.student.exception.StudentAlreadyExists;
import com.student.info.student.exception.StudentNotFoundException;
import com.student.info.student.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository repository;
    //private final CourseService courseService;
    private final ModelMapper map;
    private final EducationFeignConfig feign;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Long createStudent(Studentdto student){
        Optional<StudentEntity> existingStudent= repository.findByName(student.getName());
        if(existingStudent.isPresent()){
            throw new StudentAlreadyExists("Student with name :" +student.getName());
        }else{
            StudentEntity studentEntity=map.map(student,StudentEntity.class);
            studentEntity.getCourse().clear();
            student.getCourseName().forEach(courseName -> {
                CourseEntity courseEntity = new CourseEntity();
                courseEntity.setCourseName(courseName);
                courseEntity.setStudent(studentEntity);   // owning side
                studentEntity.getCourse().add(courseEntity); // inverse side

            });

            repository.save(studentEntity);
            return studentEntity.getId();
        }
    }

    public Studentdto getStudent(Long studentId) {
        log.info("starting getStudent :"+ studentId);
        Optional<StudentEntity> studentEntity=repository.findById(studentId);
        if(studentEntity.isPresent()){
            Studentdto studentdto=map.map(studentEntity.get(),Studentdto.class);
            List<String> courseName=new ArrayList<>();
            studentEntity.get().getCourse().forEach((course)->{
                courseName.add(course.getCourseName());
            });
            studentdto.setCourseName(courseName);
            List<Educationdto> educationDetails= feign.getEducation(studentId);
            studentdto.setEducation(educationDetails);
            return studentdto;
        }else{
            throw new StudentNotFoundException("Student not found : "+studentId);
        }
    }

    public Studentdto updateStudent(Studentdto student) {
        Optional<StudentEntity> studentEntity=repository.findById(student.getId());
        if(studentEntity.isPresent()){
            StudentEntity oldStudent =studentEntity.get();
            oldStudent.setName(student.getName());
            repository.save(oldStudent);
            return map.map(repository.findById(student.getId()).get(),Studentdto.class);
        }else{
            throw new StudentNotFoundException("Student not found for update : "+student.getId());
        }
    }

    public void deleteStudent(Long studentID) {
        Optional<StudentEntity> studentEntity=repository.findById(studentID);
        if(studentEntity.isPresent()){
            repository.deleteById(studentID);
        }else{
            throw new StudentNotFoundException("Student not found for Delete : "+studentID);
        }
    }
}
