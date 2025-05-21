package org.eric.guardpost.guardpostapi.model;

import lombok.Getter;
import lombok.Setter;


public class EmailValidationResult {

    @Getter @Setter String email;
    @Getter @Setter private String message;
    @Getter @Setter private boolean valid;

    //Constructor for Spring
    public EmailValidationResult() {}

    /**
     *
     * @param email User input to validate.
     * @param message Validation results message.
     * @param valid Boolean true/false depending on validation result.
     */

    public EmailValidationResult(String email, String message, boolean valid) {
        this.email = email;
        this.message = message;
        this.valid = valid;
    }
}
