package org.eric.guardpost.guardpostapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eric.guardpost.guardpostapi.service.RiskTier;

import java.util.ArrayList;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailValidationResult {
    /** User input to validate */
    private String email;

    /** Validation results message */
    private String message;

    /** Mx record lookup results */
    private boolean hasMxRecord;

    /** Disposable check results */
    private boolean disposable;

    /** Boolean true/false depending on validation results */
    private boolean valid;

    /** Each validation result will have a Risk Score, calculated by the sum of points earned during validation */
    private int riskScore;

    /** Each validation result will have a Risk Tier determined by its Risk Score */
    private RiskTier riskTier;


}
