package org.eric.guardpost.guardpostapi.service;

import org.springframework.stereotype.Service;
import java.util.regex.*;

@Service
public class EmailValidationService {

    //Constructor for service class - does not need any parameters
    public EmailValidationService() {}

    //Method for checking if boolean is valid against regex
    public boolean isValid(String email){

        //If email is null or empty, return a false boolean
        if (email == null || email.isEmpty()) {
            return false;
        }

        //Regex that email must abide by to be true
        String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
                "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";

        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(email).matches();
    }


}
