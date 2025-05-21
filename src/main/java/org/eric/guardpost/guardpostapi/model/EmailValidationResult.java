package org.eric.guardpost.guardpostapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailValidationResult {
    /** User input to validate */
    private String email;

    /** Validation results message */
    private String message;

    /** Boolean true/false depending on validation results */
    private boolean valid;


}
