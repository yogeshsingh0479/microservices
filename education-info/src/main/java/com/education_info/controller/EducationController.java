package com.education_info.controller;

import com.education_info.dto.Educationdto;
import com.education_info.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService service;


    @PostMapping("create")
    public ResponseEntity<Long> saveEducation(@RequestBody Educationdto educationdto){
        Long id=service.saveEducation(educationdto);
        return new ResponseEntity<>(id,HttpStatus.CREATED);//ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("info/{id}")
    public ResponseEntity<List<Educationdto>> getEducation(@PathVariable(name = "id") Long studentId){
        return new ResponseEntity<>(service.getEducation(studentId),HttpStatus.OK);//ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
}
