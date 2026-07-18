package com.vishnu.io.streamapp.error;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vishnu.io.streamapp.shared.ApplicationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

   private static final Logger LOGGER = LogManager.getLogger();

   // ExceptionHandler for all the custom exceptions thrown by the application.
   @ExceptionHandler(ApplicationException.class)
   ProblemDetail handleApplicationException(ApplicationException ex) {
      ProblemDetail problem = ProblemDetail.forStatusAndDetail(ex.getHttpStatus(), ex.getMessage());
      problem.setProperty("errorCode", ex.getErrorCode());
      problem.setProperty("timestamp", Instant.now());
      return problem;
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
      ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
      problem.setTitle("Validation Failed");
      Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(
                  f -> f.getField(),
                  f -> f.getDefaultMessage() == null ? "Invalid Value" : f.getDefaultMessage(),
                  (a, b) -> a));
      problem.setProperty("error", fieldErrors);
      problem.setProperty("timestamp", Instant.now());
      return problem;
   }

   @ExceptionHandler(Exception.class)
   ProblemDetail handleException(Exception ex) {
      ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal Server Error");
      LOGGER.error("Unhandled Exception Occured" + ex);
      problem.setProperty("errorCode", "Internal server error");
      problem.setProperty("timestamp", Instant.now());
      return problem;
   }
}
