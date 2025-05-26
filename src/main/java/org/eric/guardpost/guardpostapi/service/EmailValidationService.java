package org.eric.guardpost.guardpostapi.service;

import org.eric.guardpost.guardpostapi.model.EmailValidationResult;
import org.eric.guardpost.guardpostapi.util.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.*;

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

    /** Returns result object */
    public EmailValidationResult validate(String email) {

        // EmailUtil extractDomain method will store everything from @ onward as the domain
        String domain = EmailUtils.extractDomain(email);

        // Running domain through Mx and disposable checks
        boolean hasMxRecord = domain != null && mxLookupService.hasMxRecord(domain);
        boolean isDisposable = domain != null && disposableEmailService.isDisposable(domain);
        boolean isValid = syntaxCheck(email) && hasMxRecord && !isDisposable;
        String message;
        if (email == null || email.isEmpty() || !syntaxCheck(email)) {
            message = "Invalid email syntax";
        } else if (!hasMxRecord) {
            message = "Domain has no MX record";
        } else if (isDisposable) {
            message = "Disposable email address";
        } else {
            message = "Email validated";
        }

        return new EmailValidationResult(email, message, hasMxRecord, isDisposable, isValid);
    }

    /** Creating a separate method to check syntax validity */
    public boolean syntaxCheck(String email){

        // If email is null or empty, return a false boolean
        if (email == null || email.isEmpty()) {
            return false;
        }

        //Regex that email must abide by to be true
        String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
                "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(email).matches();
    }


}
