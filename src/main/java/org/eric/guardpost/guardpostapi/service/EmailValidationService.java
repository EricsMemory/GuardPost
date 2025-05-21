package org.eric.guardpost.guardpostapi.service;

import org.springframework.stereotype.Service;
import java.util.regex.*;

@Service
public class EmailValidationService {

    public EmailValidationService() {}

    public boolean isValid(String email){
        if (email == null || email.isEmpty()) {
            return false;
        }

        String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
                "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";

        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(email).matches();
    }


}
