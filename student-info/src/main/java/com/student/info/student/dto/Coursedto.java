package com.student.info.student.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Coursedto {
    private Long id;
    private String courseName;
    private LocalDateTime createTimeStamp;
}
