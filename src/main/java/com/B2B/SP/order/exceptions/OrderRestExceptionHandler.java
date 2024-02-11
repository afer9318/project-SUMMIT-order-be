package com.B2B.SP.order.exceptions;

import com.B2B.SP.order.exceptions.customexceptions.BadRequestException;
import com.B2B.SP.order.exceptions.customexceptions.OrderItemNotFoundException;
import com.B2B.SP.order.exceptions.customexceptions.OrderNotFoundException;
import com.B2B.SP.order.exceptions.errorresponses.ErrorResponse;
import com.B2B.SP.order.exceptions.errorresponses.ValidationErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class OrderRestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(OrderRestExceptionHandler.class);

    @ExceptionHandler({OrderNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException exception){

        // Create error response
        ErrorResponse error = new ErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        logger.error("OrderNotFoundException. Status: {}. Message: {}. Timestamp: {}",
                error.getStatus(),
                error.getMessage(),
                error.getTimestamp());

        // return responseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({OrderItemNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleOrderItemNotFoundException(OrderItemNotFoundException exception){

        // Create error response
        ErrorResponse error = new ErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        logger.error("OrderItemNotFoundException. Status: {}. Message: {}. Timestamp: {}",
                error.getStatus(),
                error.getMessage(),
                error.getTimestamp());

        // return responseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(Exception exception) {

        // create a ProductErrorResponse
        ErrorResponse error = new ErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        logger.error("BadRequestException. Status: {}. Message: {}. Timestamp: {}",
                error.getStatus(),
                error.getMessage(),
                error.getTimestamp());

        // return responseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    // Validation exception for DTOs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        logger.error("Validation exception", exception);

        return ResponseEntity.badRequest().body(new ValidationErrorResponse(HttpStatus.BAD_REQUEST, "Validation errors", errors));
    }

    // Generic exception
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleOrderServiceException(Exception exception){

        // create a ProductErrorResponse
        ErrorResponse error = new ErrorResponse();

        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        logger.error("OrderServiceException. Status: {}. Message: {}. Timestamp: {}",
                error.getStatus(),
                error.getMessage(),
                error.getTimestamp());

        // return responseEntity
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
