package org.eric.guardpost.guardpostapi.controller;

import org.eric.guardpost.guardpostapi.model.EmailValidationResult;
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

    /** Constructor that brings along the emailValidationService and disposableEmailService objects where ever they are made */
    public EmailValidationController(EmailValidationService emailValidationService) {
        this.emailValidationService = emailValidationService;
    }

    // Mapping /validate to this method
    @GetMapping("/validate")
    public ResponseEntity<EmailValidationResult> validateEmail(@RequestParam String email) {
        EmailValidationResult result = emailValidationService.validate(email);
        if (result.isValid()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }

    }
}
