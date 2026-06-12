package com.student.info.student.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String msg){
        super(msg);
    }
}
