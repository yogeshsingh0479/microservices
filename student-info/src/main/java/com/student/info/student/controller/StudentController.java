package com.student.info.student.controller;

import com.student.info.student.dto.Studentdto;
import com.student.info.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {
    private final StudentService service;
    private Object studentID;

    @GetMapping("getstudent/{id}")
    public ResponseEntity<Studentdto> getStudent(@PathVariable(name = "id") Long studentId){
        Studentdto studentdto=service.getStudent(studentId);
        return new ResponseEntity<>(studentdto, HttpStatus.FOUND);
    }

    @PostMapping("create")
    public ResponseEntity<Long> createStudent(@RequestBody Studentdto student){
        Long studentId= service.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentId);
    }

    @PutMapping("update")
    public ResponseEntity<Studentdto> updatestudent(@RequestBody Studentdto student){
        Studentdto studentdto=service.updateStudent(student);
        return new ResponseEntity<>(studentdto, HttpStatus.FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(name="id") Long studentID){
        service.deleteStudent(studentID);
        return new ResponseEntity<>("Student Deleted successfully!!!", HttpStatus.OK);
    }
}
