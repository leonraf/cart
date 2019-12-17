package it.mandese.carrello.controllers;

import it.mandese.carrello.exceptions.CartItemNotFoundException;
import it.mandese.carrello.exceptions.CartNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler
{

  final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);

  @ExceptionHandler(value = { CartNotFoundException.class})
  public ResponseEntity<String> handleCartNotFound() {
    return new ResponseEntity<String>("cart not found", HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = { CartItemNotFoundException.class})
  public ResponseEntity<String> handleCartItemNotFound() {
    return new ResponseEntity<String>("cart item not found", HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = { Exception.class})
  public ResponseEntity<String> handleGenericError(Exception e) {
    LOG.error("", e);
    return new ResponseEntity<String>("An error was occured!", HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
