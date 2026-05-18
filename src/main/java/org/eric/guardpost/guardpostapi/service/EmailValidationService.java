package org.eric.guardpost.guardpostapi.service;

import org.eric.guardpost.guardpostapi.model.EmailValidationResult;
import org.eric.guardpost.guardpostapi.util.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class EmailValidationService {

    private final MxLookupService mxLookupService;
    private final DisposableEmailService disposableEmailService;


    /** Constructor for validation service class */
    @Autowired
    public EmailValidationService(MxLookupService mxLookupService, DisposableEmailService disposableEmailService) {
        this.mxLookupService = mxLookupService;
        this.disposableEmailService = disposableEmailService;
    }

    private RiskTier determineTier(int score) {
        if (score >= 90) return RiskTier.LOW;
        if (score >= 65) return RiskTier.MEDIUM;
        if (score >= 40) return RiskTier.HIGH;
        return RiskTier.CRITICAL;
    }

    /** Returns result object */
    public EmailValidationResult validate(String email) {

        // EmailUtil extractDomain method will store everything from @ onward as the domain
        String domain = EmailUtils.extractDomain(email);

        // Running domain through Mx and disposable checks
        boolean hasMxRecord = domain != null && mxLookupService.hasMxRecord(domain);
        boolean isDisposable = domain != null && disposableEmailService.isDisposable(domain);
        boolean isValid = EmailUtils.syntaxCheck(email) && hasMxRecord && !isDisposable;
        int score = 100;
        ArrayList<String> reasons = new ArrayList<>();

        if (email == null || email.isEmpty() || !EmailUtils.syntaxCheck(email)) {
            score -= 40;
            reasons.add("Invalid email syntax");
        }

        if (!hasMxRecord) {
            score -= 40;
            reasons.add("Domain has no MX record");
        }

        if (isDisposable) {
            score -= 30;
            reasons.add("Disposable email address");
        }

        score = Math.max(score, 0);
        RiskTier tier = determineTier(score);

        String message = reasons.isEmpty() ? "Email validated" : String.join(", ", reasons);

        return new EmailValidationResult(email, message, hasMxRecord, isDisposable, isValid, score, tier);
    }




}
