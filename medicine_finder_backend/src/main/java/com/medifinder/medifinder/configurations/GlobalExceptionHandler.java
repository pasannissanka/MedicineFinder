package com.medifinder.medifinder.configurations;

import com.medifinder.medifinder.dto.responses.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;


@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
//    private final Logger logger;

    @ExceptionHandler(BindValidationException.class)
    public ResponseEntity<Response<Object>> handleValidationException(HttpServletRequest request, BindValidationException ex) {

        Response<Object> response = Response.badRequest();
        response.addErrorMsgToResponse("Validation Exception", ex.getValidationErrors());

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Response<Object>> handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException ex) {
//        logger.error("handleMissingServletRequestParameterException {}\n", request.getRequestURI(), ex);

        Response<Object> response = Response.badRequest();
        response.addErrorMsgToResponse("Missing request parameter", ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Response<Object>> handleMethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException ex) {
//        logger.error("handleMethodArgumentTypeMismatchException {}\n", request.getRequestURI(), ex);

        Map<String, String> details = new HashMap<>();
        details.put("paramName", ex.getName());
        details.put("paramValue", ofNullable(ex.getValue()).map(Object::toString).orElse(""));
        details.put("errorMessage", ex.getMessage());

        Response<Object> response = Response.badRequest();
        response.addErrorMsgToResponse("Method argument type mismatch", details);

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Object>> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {
//        logger.error("handleMethodArgumentNotValidException {}\n", request.getRequestURI(), ex);

        List<Map<String, String>> details = new ArrayList<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> {
                    Map<String, String> detail = new HashMap<>();
                    detail.put("objectName", fieldError.getObjectName());
                    detail.put("field", fieldError.getField());
                    detail.put("rejectedValue", "" + fieldError.getRejectedValue());
                    detail.put("errorMessage", fieldError.getDefaultMessage());
                    details.add(detail);
                });

        Response<Object> response = Response.badRequest();
        response.addErrorMsgToResponse("Method argument validation failed", details);

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response<Object>> handleAccessDeniedException(HttpServletRequest request, AccessDeniedException ex) {
//        logger.error("handleAccessDeniedException {}\n", request.getRequestURI(), ex);

        Response<Object> response = Response.accessDenied();
        response.addErrorMsgToResponse("Access denied!", List.of(ex.getMessage()));

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Object>> handleInternalServerError(HttpServletRequest request, Exception ex) {
//        logger.error("handleInternalServerError {}\n", request.getRequestURI(), ex);

        Response<Object> response = Response.exception();
        response.addErrorMsgToResponse("Internal server error", List.of(ex.getMessage()));

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public ResponseEntity<Response<Object>> handleValidationErrors(HttpServletRequest request, Exception ex) {
//        logger.error("handleInternalServerError {}\n", request.getRequestURI(), ex);

        Response<Object> response = Response.validationException();
        response.addErrorMsgToResponse("Validation Error", List.of(ex.getMessage()));

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(response);
    }
}