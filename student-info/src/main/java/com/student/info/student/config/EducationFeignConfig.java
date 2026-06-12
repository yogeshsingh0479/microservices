package com.student.info.student.config;

import com.student.info.student.dto.Educationdto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "education-info")
public interface EducationFeignConfig {

    @GetMapping("education/info/{id}")
    List<Educationdto> getEducation(@PathVariable(name = "id") Long studentId);
}
