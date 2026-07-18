package com.vishnu.io.streamapp.shared;

import org.springframework.http.HttpStatus;

public abstract class ApplicationException extends RuntimeException {
   private final HttpStatus status;
   private final String errorCode;

   protected ApplicationException(HttpStatus status, String errorCode, String message) {
      super(message);
      this.status = status;
      this.errorCode = errorCode;
   }

   protected ApplicationException(HttpStatus status, String errorCode, String message, Throwable cause) {
      super(message, cause);
      this.status = status;
      this.errorCode = errorCode;
   }

   public String getErrorCode() {
      return this.errorCode;
   }

   public HttpStatus getHttpStatus() {
      return this.status;
   }
}
