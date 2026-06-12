package com.student.info.student.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Studentdto {
    private Long id;
    private String name;
    private LocalDateTime createTimeStamp;
    private List<String> courseName;
    private List<Educationdto> education;
}
