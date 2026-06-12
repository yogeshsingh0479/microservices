package com.student.info.student.globalexception;

import com.student.info.student.error.StudentError;
import com.student.info.student.exception.StudentAlreadyExists;
import com.student.info.student.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentExceptionHandler extends RuntimeException{

    @ExceptionHandler(StudentAlreadyExists.class)
    public ResponseEntity<StudentError> handleStudentAlreadyExists(StudentAlreadyExists msg){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(StudentError.builder().msg(msg.getMessage()).build());
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<StudentError> handleStudentNotFoundException(StudentNotFoundException msg){
        StudentError error=StudentError.builder().msg(msg.getMessage()).status("Student Not Found").build();
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}
