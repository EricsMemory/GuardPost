package org.eric.guardpost.guardpostapi.controller;

import org.eric.guardpost.guardpostapi.model.EmailValidationResult;
import org.eric.guardpost.guardpostapi.service.DisposableEmailService;
import org.eric.guardpost.guardpostapi.service.EmailValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Tells Spring Boot that this class handles web requests and sends data
@RestController
public class EmailValidationController {

    // Service that checks email validity
    private final EmailValidationService emailValidationService;
    private final DisposableEmailService disposableEmailService;

    /** Constructor that brings along the emailValidationService and disposableEmailService objects where ever they are made */
    public EmailValidationController(EmailValidationService emailValidationService, DisposableEmailService disposableEmailService) {
        this.emailValidationService = emailValidationService;
        this.disposableEmailService = disposableEmailService;
    }

    // Mapping /validate to this method
    @GetMapping("/validate")
    public ResponseEntity<EmailValidationResult> validateEmail(@RequestParam String email) {

        // Uses isValid method from emailValidationService class to determine email validity
        boolean isValid = emailValidationService.isValid(email);
        boolean isDisposable = disposableEmailService.isDisposable(email);

        // If the domain is in the disposable conf file, set isValid to false
        if (isDisposable){
            isValid = false;
        }

        // Create unique message depending on email validity
        String message = isValid ? "Email validated" : "Email invalid";

        // Creates a result object with the user inputted email, the result message, and the validity boolean
        EmailValidationResult result = new EmailValidationResult(email, message, isDisposable, isValid);

        //If it is valid, send back the result with a 200 OK response
        if (isValid) {
            return ResponseEntity.ok(result);
        }
        // Otherwise return the result with a 400 Bad Request response
        else {
            return ResponseEntity.badRequest().body(result);
        }

    }
}
