package com.tential.departmentinfo.controller;

import com.tential.departmentinfo.exception.DepartmentNotFoundException;
import com.tential.departmentinfo.exception.ExceptionResponse;
import com.tential.departmentinfo.exception.InvalidDepartmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class DepartmentExceptionController {

    @Autowired
    private ExceptionResponse exceptionResponse;

    @ExceptionHandler(value = DepartmentNotFoundException.class)
    public ResponseEntity<ExceptionResponse> DepartmentNotFound(DepartmentNotFoundException exception, final HttpServletRequest request) {
        exceptionResponse.setErrorMessage("Department Id Not Found");
        exceptionResponse.callerURL(request.getRequestURI());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidDepartmentException.class)
    public ResponseEntity<ExceptionResponse> InvalidDepartment(InvalidDepartmentException exception, final HttpServletRequest request) {
        exceptionResponse.setErrorMessage("Input Data is Not proper! Either Department Name is Null or the Count is Not Proper");
        exceptionResponse.callerURL(request.getRequestURI());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(final Exception exception,
                                                           final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.callerURL(request.getRequestURI());
        return new ResponseEntity<ExceptionResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
