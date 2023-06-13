package com.cfm.Yolo.domain.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cfm.Yolo.domain.dto.ErrorDto;
import com.cfm.Yolo.domain.exception.InvalidPasswordException;
import com.cfm.Yolo.domain.exception.PersonNotFoundException;
import com.cfm.Yolo.domain.exception.UserAlreadyExistException;
import com.cfm.Yolo.domain.exception.UserNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ UserNotFoundException.class, PersonNotFoundException.class })
  public ResponseEntity<ErrorDto> handleUserNotFound(RuntimeException ex, WebRequest request) {
    var errorDto = new ErrorDto(404, "Usuário não encontrado", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
  }

  @ExceptionHandler(UserAlreadyExistException.class)
  public ResponseEntity<ErrorDto> handleUserAlreadyExist(RuntimeException ex, WebRequest request) {
    var errorDto = new ErrorDto(409, "Usuário já existe", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
  }

  @ExceptionHandler(InvalidPasswordException.class)
  public ResponseEntity<ErrorDto> handleInvalidPassword(RuntimeException ex, WebRequest request) {
    var errorDto = new ErrorDto(401, "Senha inválida", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorDto> handleOtherExceptions(RuntimeException ex, WebRequest request) {
    var errorDto = new ErrorDto(500, "Erro interno do servidor", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
  }

}
