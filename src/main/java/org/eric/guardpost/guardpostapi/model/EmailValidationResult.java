package org.eric.guardpost.guardpostapi.model;


public class EmailValidationResult {

    private String email;
    private String message;
    private boolean valid;

    public EmailValidationResult() {}
    public EmailValidationResult(String email, String message, boolean valid) {
        this.email = email;
        this.message = message;
        this.valid = valid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
