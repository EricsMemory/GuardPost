package org.eric.guardpost.guardpostapi.model;


public class EmailValidationResult {


    public EmailValidationResult() {}
    public EmailValidationResult(String email, String message, boolean valid) {
        this.email = email;
        this.message = message;
        this.valid = valid;
    }
}
