package org.eric.guardpost.guardpostapi.util;

import java.util.regex.Pattern;

/** Utility class with non-specific methods */
public class EmailUtils {

    /** Method for extracting domain out of a full email address */
    public static String extractDomain(String email){
        if (email == null || !email.contains("@")){
            return null;
        }
        return email.substring(email.indexOf("@")+1);
    }

    /**
     * Creating a separate method to check syntax validity
     * Boolean requires an email as a parameter
     * If the email is null or .isEmpty() is true against it, this boolean returns false.
     * Otherwise, it continues to create a String regex (widely appreciated as email standard),
     * and returns true only if the email matches the required pattern.
     */
    public static boolean syntaxCheck(String email){

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
