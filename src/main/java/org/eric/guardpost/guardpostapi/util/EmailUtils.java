package org.eric.guardpost.guardpostapi.util;

import java.util.regex.Pattern;

/** Utility class with non-specific methods */
public class EmailUtils {
    /**
     * Method for extracting domain out of a full email address
     * If the email is null or does not contain an "@", extractDomain returns null.
     * Otherwise, it continues to slice a substring of the email beginning at the character
     * after the "@" symbol.
     * extractDomain returns that slice.
     */
    public static String extractDomain(String email){
        if (email == null || !email.contains("@")){
            return null;
        }
        return email.substring(email.indexOf("@")+ 1);
    }

    /**
     * Creating a separate method to check syntax validity
     * Boolean requires an email as a parameter
     * If the email is null or .isEmpty() is true against it, this boolean returns false.
     * Otherwise, it continues to create a String regex (widely appreciated as email standard),
     * and returns true only if the email matches the required pattern.
     */
    public static boolean syntaxCheck(String email){
        if (email == null || email.isEmpty()) {
            return false;
        }

        String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
                "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(email).matches();
    }
}
