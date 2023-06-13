// package com.cfm.Yolo.domain.configurations;

// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.context.request.WebRequest;
// import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// import com.cfm.Yolo.domain.dto.ErrorDto;

// @ControllerAdvice
// public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

//   @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
//   protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
//     String bodyOfResponse = "This should be application specific";
//     return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
//     // return ResponseEntity.ok(new ErrorDto(500, bodyOfResponse, ex.getCause()));
//   }

// }
