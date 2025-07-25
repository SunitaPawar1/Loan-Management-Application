package com.loanmanagement.loansystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NoLoanHistoryFoundException.class)
    public ResponseEntity<String> handleNoLoanHistoryFoundException(NoLoanHistoryFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	
    //handle user not found for WebController
    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFound(UserNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error"; // error.html
    }
    //handle generic exception (both web & rest fallback)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        ex.printStackTrace();
        model.addAttribute("error", ex.toString());
        return "errorPage";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException ex, Model model) {
        ex.printStackTrace();
        model.addAttribute("error", ex.toString());
        return "errorPage";
    }

}
